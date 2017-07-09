package com.shijin.learn.movingdemo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
  private static final Logger LOGGER = LogManager.getLogger(ResourceServerConfiguration.class);
  @Override
  public void configure(HttpSecurity http) throws Exception {
    LOGGER.trace("configure ResourceServerConfiguration...");
    http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
  }
}
