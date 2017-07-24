/**
 * 
 */
package com.shijin.learn.movingdemo.service;

import java.util.ArrayList;
import java.util.Collection;

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
import com.shijin.learn.movingdemo.adapter.LoginUser;

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
  
  public Collection<LoginUser> getUserList() {
    Collection<LoginUser> collection = new ArrayList<>();
    LoginUser user = new LoginUser();
    user.setId(1l);
    user.setCompany("Learn");
    user.setUsername("Shijin");
    user.setRoles("Admin, User");
    collection.add(user);
    
    user = new LoginUser();
    user.setId(2l);
    user.setCompany("Learn");
    user.setUsername("Jeri Shi");
    user.setRoles("User");
    collection.add(user);
    
    LOGGER.trace("/userslist will return: " + collection);
    return collection;
  }
}
