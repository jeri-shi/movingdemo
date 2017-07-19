/**
 * 
 */
package com.shijin.learn.movingdemo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author shijin
 *
 */
@Service
public class UsersService {
  private static final Logger LOGGER = LogManager.getLogger(UsersService.class);
  @Autowired
  private RestTemplate restTemplate;

//  @SessionScope
//  @RequestScope
//  @HystrixCommand(fallbackMethod = "getUserFallback",
//      commandProperties = {
//          @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
//        })
  public String getUser(Integer id) {
    String userString = restTemplate
        .getForEntity("http://MOVINGDEMO-USERS/client/user/{1}", String.class, id)
        .getBody();
    LOGGER.debug("USER_SERVICE.getUser()={}", userString);
    return userString;
  }
  
  @SuppressWarnings("unused")
  private String getUserFallback(Integer id) {
    return "Default User:" + id;
  }
}
