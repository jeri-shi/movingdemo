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
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author shijin
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
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
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }
  
}
