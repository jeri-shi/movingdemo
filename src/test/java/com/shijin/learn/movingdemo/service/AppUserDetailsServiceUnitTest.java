/**
 * Copyright 2017 Shi Jin Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.shijin.learn.movingdemo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.shijin.learn.movingdemo.config.DebugConfig;
import com.shijin.learn.movingdemo.config.RootConfig;
import com.shijin.learn.movingdemo.service.dao.AppCompanyUser;
import com.shijin.learn.movingdemo.service.dao.AppUser;
import com.shijin.learn.movingdemo.service.dao.Authority;
import com.shijin.learn.movingdemo.service.mapper.UserMapper;

/**
 * @author shijin
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DebugConfig.class})
@ActiveProfiles("dev")
public class AppUserDetailsServiceUnitTest {

  @Mock
  UserMapper userMapper;

  @Autowired
  @InjectMocks
  AppUserDetailsService appUserDetailsService;  //inject mocked userMapper here
  
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testServiceWithMockMapper() {
    //Given
    AppUser jeri = new AppUser();
    List<Authority> list = new ArrayList<Authority>(1);
    Authority role = new Authority();
    role.setRole("USER");
    list.add(role);
    jeri.setName("Jeri Shi");
    jeri.setPwd("pass");
    jeri.setAuthorities(list);
    System.out.println(userMapper);
    given(userMapper.loadAppUserByUserName("Jeri"))
      .willReturn(jeri);
    
    //when service is invoked
    UserDetails userDetails = appUserDetailsService.loadUserByUsername("Jeri");
    
    //then
    assertEquals("Jeri Shi", userDetails.getUsername());
  }
  
  @Test
  public void testLoadUserByCompanyUsername() {
    //given
    AppCompanyUser returnUser = new AppCompanyUser();
    String company = "company";
    String username = "usernmae";
    returnUser.setCompany(company);
    returnUser.setName(username);
    given(userMapper.loadAppUserByCompanyUserName(company, username)).willReturn(returnUser);
    
    //when
    AppCompanyUser user = appUserDetailsService.loadUserByCompanyUsername(company, username);
    
    //then
    assertEquals(returnUser, user);
  }
   
  @Test
  public void testLoadUserByCompanyUsername_isNull() {
    //given
    AppCompanyUser returnUser = new AppCompanyUser();
    String company = "company";
    String username = "usernmae";
    returnUser.setCompany(company);
    returnUser.setName(username);
    given(userMapper.loadAppUserByCompanyUserName(company, username)).willReturn(null);
    thrown.expect(UsernameNotFoundException.class);
    
    //when
    appUserDetailsService.loadUserByCompanyUsername(company, username);

  }
  
}
