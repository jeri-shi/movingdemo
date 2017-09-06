/**
 * 
 */
package com.shijin.learn.movingdemo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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
  
  @Autowired
  private RedisTemplate<String, Resource> imageRedisTemplate;

//  @SessionScope
//  @RequestScope
//  @HystrixCommand(fallbackMethod = "getUserFallback",
//      commandProperties = {
//          @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
//        })
  public LoginUser getUser(long id) {
    LoginUser user = restTemplate
        .getForEntity("http://MOVINGDEMO-USERS/client/user/{1}", LoginUser.class, id)
        .getBody();
    LOGGER.debug("USER_SERVICE.getUser()={}", user);
    return user;
  }
  
  public LoginUser updateUser(LoginUser user) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<LoginUser> request = new HttpEntity<LoginUser>(user , headers);
    ResponseEntity<LoginUser> response =
        restTemplate.exchange("http://MOVINGDEMO-USERS/client/user/{1}", HttpMethod.PUT,
            request, new ParameterizedTypeReference<LoginUser>() {}, user.getId());
    
    LoginUser returnUser = response.getBody();
    return returnUser;
  }
 
  public Integer deleteUser(long id) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    ResponseEntity<Integer> response =
        restTemplate.exchange("http://MOVINGDEMO-USERS/client/user/{1}", HttpMethod.DELETE,
            null, new ParameterizedTypeReference<Integer>() {}, id);
    
    Integer returnUser = response.getBody();
    
    //if delete user successfully, then clear the cache
    if (returnUser == 1) {
      imageRedisTemplate.execute(new RedisCallback<Object>() {

        @Override
        public Object doInRedis(RedisConnection connection) throws DataAccessException {
          byte[] key = ("photo_" + id).getBytes();
          connection.del(key);
          connection.zRem("photos~keys".getBytes(), key);
          LOGGER.debug("clear cache for " + key);
          return null;
        }
      }, true, true);
      
    }
    return returnUser;
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
 
  @Cacheable(value = "photos", key="'photo_'+#id")
  public Resource getUserPhoto(long id) {
    //get image from cache, if not get it from users micro-service.
    
    //put image into redis cache, set exprie time to 20 seconds
    Resource image = restTemplate.getForObject("http://MOVINGDEMO-USERS/client/user/{id}/photo", Resource.class, id);
    return image;
  }
  
  public String store(long id, MultipartFile imageFile) {
    LOGGER.debug("id = {}, multiFile = {}", id,  imageFile);
    
    //prepare request for file upload
    LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
    map.add("filename", imageFile.getName());
    ByteArrayResource resource = null;
    try {
      resource = new ByteArrayResource(imageFile.getBytes()) {
        @Override
        public String getFilename() {
          return imageFile.getName();
        }
      };
      map.add("file", resource);
    } catch (IOException e) {
      LOGGER.error("error: {}", e.getMessage());
    }
    
    HttpHeaders headers = new HttpHeaders();
    //headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    
    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = 
        new HttpEntity<LinkedMultiValueMap<String,Object>>(map, headers);
    
    LOGGER.debug("Prepare request = {}", requestEntity);
    ResponseEntity<String> response = 
        restTemplate.exchange("http://MOVINGDEMO-USERS/client/user/{id}/photo", HttpMethod.POST, requestEntity, String.class, id);
    String photoName = response.getBody();
    LOGGER.debug("response={}", photoName);

    //if response.getBody is start with "photo" then update cache
    if (photoName != null && photoName.startsWith("head")) {
      imageRedisTemplate.boundValueOps("photo_" + id).set(resource);
      LOGGER.debug("update cache for photo_" + id);
    }
    
    return response.getBody();
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
