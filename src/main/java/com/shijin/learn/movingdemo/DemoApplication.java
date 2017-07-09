/**
 * 
 */
package com.shijin.learn.movingdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.client.RestTemplate;


/**
 * @author shijin
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAuthorizationServer

public class DemoApplication {
  private static final Logger LOGGER = LogManager.getLogger(DemoApplication.class);
  /**
   * @param args
   */
  public static void main(String[] args) {
    LOGGER.info("Demo Application is starting...");
    SpringApplication.run(DemoApplication.class, args);

  }
  
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }
  
  
  @Configuration
  @EnableResourceServer
  public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
      LOGGER.trace("configure ResourceServerConfiguration...");
      http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
    }
  }
}
