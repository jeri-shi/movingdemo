/**
 * Copyright 2017 Shi Jin Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.shijin.learn.movingdemo.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.shijin.learn.movingdemo.service.CompanyUserAuthenticationProvider;

/**
 * @author shijin
 *
 */
@Configuration
@ComponentScan("com.shijin.learn.movingdemo.service")
@MapperScan("com.shijin.learn.movingdemo.service.mapper")
public class RootConfig {
  private static final Logger LOGGER = LogManager.getLogger(RootConfig.class);

//  @Bean
//  @Resource(name = "jdbc/datasource")
//  @Profile("default")
//  public DataSource dataSource() {
//    LOGGER.debug("init dataSource");
//    final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//    dsLookup.setResourceRef(true);
//    return dsLookup.getDataSource("jdbc/datasource");
//  }
  /**
   * Initial embedded tomcat with jndi support
   * @see https://stackoverflow.com/questions/24941829/how-to-create-jndi-context-in-spring-boot-with-embedded-tomcat-container
   * @see https://github.com/wilkinsona/spring-boot-sample-tomcat-jndi/blob/master/src/main/java/sample/tomcat/jndi/SampleTomcatJndiApplication.java
   * @return
   */
  @Bean
  @Profile("embedded")
  public EmbeddedServletContainerFactory servletContainer() {
      LOGGER.info("initiate embedded tomcat.");  
      return new TomcatEmbeddedServletContainerFactory(){
        @Override
        protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
          tomcat.enableNaming();
          return super.getTomcatEmbeddedServletContainer(tomcat);
        }
        
        @Override
        protected void postProcessContext(Context context) {
          ContextResource resource = new ContextResource();
          resource.setName("jdbc/dataSource");
          resource.setType(DataSource.class.getName());
          resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
          resource.setProperty("url", "jdbc:mysql://localhost:3306/hello");
          resource.setProperty("username", "root");
          resource.setProperty("password", "root");
          resource.setProperty("maxActive", "5");
          resource.setProperty("maxIdle", "3");
          resource.setProperty("maxWait", "10000");
              
          context.getNamingResources().addResource(resource);
        }
      };

  }

  @Bean(name="dataSource", destroyMethod="")
  @Profile("embedded")
  public DataSource dataSource() throws IllegalArgumentException, NamingException {
      LOGGER.info("initiate dataSource from jndi");
      JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
      bean.setJndiName("java:comp/env/jdbc/dataSource");
      bean.setProxyInterface(DataSource.class);
      bean.setLookupOnStartup(false);
      bean.afterPropertiesSet();
      return (DataSource)bean.getObject();
  }
  
  /**
   * Used by Mybatis for mapper interface
   * @param dataSource
   * @return
   * @throws Exception
   */
  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
    factory.setDataSource(dataSource); 
    return factory.getObject();
  }

//  @Bean
//  @Profile("default")
//  public MapperScannerConfigurer mapperScannerConfigurer() {
//    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//    mapperScannerConfigurer.setBasePackage("com.shijin.learn.movingdemo.service.mapper");
//    return mapperScannerConfigurer;
//  }

  @Bean
  public UserDetailsService inMemoryUserDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("ShiJin").password("111111").roles("USER").build());
    return manager;
  }

  @Bean
  public DaoAuthenticationProvider daoInMemoryProvider(UserDetailsService inMemoryUserDetailsService) {
    DaoAuthenticationProvider daoInMemoryProvider = new DaoAuthenticationProvider();
    daoInMemoryProvider.setUserDetailsService(inMemoryUserDetailsService);
    return daoInMemoryProvider;
  }
  
  @Bean
  public DaoAuthenticationProvider myBatisProvider(UserDetailsService appUserDetailsService) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(appUserDetailsService);
    return provider;
  }
  
  @Bean
  public CompanyUserAuthenticationProvider companyUserAuthenticationProvider(){
    CompanyUserAuthenticationProvider provider = new CompanyUserAuthenticationProvider();
    return provider;
  }
  
}
