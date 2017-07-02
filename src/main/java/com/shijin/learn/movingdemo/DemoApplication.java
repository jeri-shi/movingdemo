/**
 * 
 */
package com.shijin.learn.movingdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author shijin
 *
 */

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
}
