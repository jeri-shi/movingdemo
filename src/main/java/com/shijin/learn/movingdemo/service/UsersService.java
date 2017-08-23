/**
 * 
 */
package com.shijin.learn.movingdemo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shijin.learn.movingdemo.adapter.LoginUser;
import com.shijin.learn.movingdemo.adapter.UserListQueryParameters;

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
  
  public Collection<LoginUser> getUserList(UserListQueryParameters queryParameters) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<UserListQueryParameters> request = new HttpEntity<UserListQueryParameters>(queryParameters , headers);
    ResponseEntity<Collection<LoginUser>> response =
        restTemplate.exchange("http://MOVINGDEMO-USERS/client/userslist", HttpMethod.POST,
            request, new ParameterizedTypeReference<Collection<LoginUser>>() {});
 
    Collection<LoginUser> collection = response.getBody();
    return collection;
  }

  public Long getUserListCount(UserListQueryParameters queryParameters) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<UserListQueryParameters> request = new HttpEntity<UserListQueryParameters>(queryParameters , headers);
    ResponseEntity<Long> response =
        restTemplate.exchange("http://MOVINGDEMO-USERS/client/userslistcount", HttpMethod.POST,
            request, new ParameterizedTypeReference<Long>() {});
 
    Long count = response.getBody();
    return count;
  }
  
  public LoginUser addUser(LoginUser user) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<LoginUser> request = new HttpEntity<LoginUser>(user , headers);
    ResponseEntity<LoginUser> response =
        restTemplate.exchange("http://MOVINGDEMO-USERS/client/user", HttpMethod.POST,
            request, new ParameterizedTypeReference<LoginUser>() {});
 
    LoginUser returnUser = response.getBody();
    return returnUser;
    
  }

  public Collection<LoginUser> getUserListFake() {
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
