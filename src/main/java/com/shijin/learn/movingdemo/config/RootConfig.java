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

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author shijin
 *
 */
@Configuration
@ComponentScan("com.shijin.learn.movingdemo.service")
@MapperScan("com.shijin.learn.movingdemo.service.mapper")
public class RootConfig {
  private static final Logger LOGGER = LogManager.getLogger(RootConfig.class);

  @Bean
  @Resource(name = "jdbc/datasource")
  @Profile("default")
  public DataSource dataSource() {
    LOGGER.debug("init dataSource");
    final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
    dsLookup.setResourceRef(true);
    return dsLookup.getDataSource("jdbc/datasource");
  }

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
  
}
