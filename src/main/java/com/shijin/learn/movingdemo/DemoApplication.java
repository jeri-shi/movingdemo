/**
 * 
 */
package com.shijin.learn.movingdemo;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

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
        
      };

  }

}
