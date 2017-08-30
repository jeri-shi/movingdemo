package com.shijin.learn.movingdemo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.shijin.learn.movingdemo.config.DebugConfig;
import com.shijin.learn.movingdemo.config.RootConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DebugConfig.class})
@ActiveProfiles("dev")
public class UserServiceTest {

  @MockBean
  private RestTemplate restTemplate;
  
  @Autowired
  @InjectMocks
  private UsersService userService;
  
  @Test
  public void storeTest() {
    //given
    long id = 8;
    MockMultipartFile file = new MockMultipartFile("file", "test.png", MediaType.MULTIPART_FORM_DATA_VALUE, "adafdsdfs".getBytes());
    Path expectedPath = Paths.get("c:\\temp\\upload", "images", "" + id, "test.png");
    ResponseEntity<String> response = new ResponseEntity<String>(expectedPath.toString(), HttpStatus.OK);
    given(restTemplate.postForEntity(anyString(), anyObject(), eq(String.class), anyLong())).willReturn(response);
    
    //when
    String returnPath = userService.store(id, file);
    
    //then
    assertEquals("Path should be same", expectedPath.toString() , returnPath);
    
    
    
  }
  
}
