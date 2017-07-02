/**
 * 
 */
package com.shijin.learn.movingdemo;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;


/**
 * @author shijin
 *
 */

@SpringBootApplication
public class DemoApplication {

  /**
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);

  }
  
  /**
   * Initial embedded tomcat with jndi support
   * @see https://stackoverflow.com/questions/24941829/how-to-create-jndi-context-in-spring-boot-with-embedded-tomcat-container
   * @see https://github.com/wilkinsona/spring-boot-sample-tomcat-jndi/blob/master/src/main/java/sample/tomcat/jndi/SampleTomcatJndiApplication.java
   * @return
   */
  @Bean
  @Profile("embedded")
  public EmbeddedServletContainerFactory servletContainer() {
      return new TomcatEmbeddedServletContainerFactory(){
        @Override
        protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
          tomcat.enableNaming();
          return super.getTomcatEmbeddedServletContainer(tomcat);
        }
        
        @Override
        protected void postProcessContext(Context context) {
          ContextResource resource = new ContextResource();
          resource.setName("jdbc/datasource");
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

  @Bean(destroyMethod="")
  @Profile("embedded")
  public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
      JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
      bean.setJndiName("java:comp/env/jdbc/dataSource");
      bean.setProxyInterface(DataSource.class);
      bean.setLookupOnStartup(false);
      bean.afterPropertiesSet();
      return (DataSource)bean.getObject();
  }
}
