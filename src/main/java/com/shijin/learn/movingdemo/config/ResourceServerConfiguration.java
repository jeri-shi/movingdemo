package com.shijin.learn.movingdemo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

@Configuration
@EnableResourceServer
@EnableOAuth2Client
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
  private static final Logger LOGGER = LogManager.getLogger(ResourceServerConfiguration.class);

  @Override
  public void configure(HttpSecurity http) throws Exception {
    LOGGER.trace("configure ResourceServerConfiguration...");
    
    http.
    antMatcher("/me").authorizeRequests()
    .anyRequest().authenticated();
  }

  @Autowired
  OAuth2ClientContext oauth2ClientContext;

  @Bean
  @LoadBalanced
  public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails resource,
      OAuth2ClientContext context) {
    LOGGER.trace("OAuth2ProtectedResourceDetails:" + resource);
    return new OAuth2RestTemplate(resource, context);
  }
//  
//  @Bean
//  @LoadBalanced
//  public OAuth2RestTemplate restTemplate() {
//      return factory.getUserInfoRestTemplate();
//  }
  
  @Bean
  public HystrixConcurrencyStrategy hystrixConcurrencyStrategy() {
    return new HystrixRequestScopeConcurrencyStrategy();
  }

//  @Bean
//  public HystrixConcurrencyStrategy requestConcurrencyStrategy() {
//    return new HystrixConcurrencyStrategy() {
//
//      @Override
//      public <T> Callable<T> wrapCallable(Callable<T> delegate) {
//        LOGGER.info("HystrixConcurrencyStrategy.wrapCallable()...");
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        LOGGER.debug("securityContext=" + securityContext);
//        return securityContext == null ? new DelegatingSecurityContextCallable<T>(delegate)
//            : new DelegatingSecurityContextCallable<T>(delegate, securityContext);
//
//      }
//    };
//  }
}
