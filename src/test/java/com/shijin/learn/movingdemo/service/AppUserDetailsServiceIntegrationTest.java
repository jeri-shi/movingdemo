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
import static org.junit.Assert.assertTrue;
import static org.springframework.util.Assert.notEmpty;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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

/**
 * @author shijin
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DebugConfig.class})
@ActiveProfiles("dev")
public class AppUserDetailsServiceIntegrationTest {

  @Autowired
  AppUserDetailsService appUserDetailsService;
  
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testLoadUserByCompanyUsername() {
    String company = "Learn";
    String username = "Jin";
    
    AppCompanyUser user = appUserDetailsService.loadUserByCompanyUsername(company, username);
    
    assertEquals(company, user.getCompany());
    assertEquals(username, user.getName());
    assertEquals("111", user.getPwd());
    assertTrue(user.isEnabled());
    notEmpty(user.getAuthorities(), "user role should not be empty.");
  }
  @Test
  public void testLoadUserByUsername() {
    // Given
    String name = "Sunny";

    // When
    UserDetails user = appUserDetailsService.loadUserByUsername(name);

    // Then
    assertEquals(name, user.getUsername());
    assertEquals("ROLE_USER", user.getAuthorities().iterator().next().getAuthority());
  }
    
  @Test
  public void testLoadUserByUsername_nobody() {
    //Given nobody
    String username = "nobody";
    thrown.expect(UsernameNotFoundException.class);
    
    //When
    appUserDetailsService.loadUserByUsername(username);
    
  }
  
  @Test
  public void testLoadUserByUsername_null() {
    //Given nobody
    String username = null;
    thrown.expect(IllegalArgumentException.class);
    
    //When
    appUserDetailsService.loadUserByUsername(username);
  }
}
