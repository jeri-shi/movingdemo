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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.shijin.learn.movingdemo.config.DebugConfig;
import com.shijin.learn.movingdemo.config.RootConfig;
import com.shijin.learn.movingdemo.service.dao.AppUser;
import com.shijin.learn.movingdemo.service.mapper.UserMapper;

/**
 * @author shijin
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DebugConfig.class})
@ActiveProfiles("dev")
public class AppUserDetailsServiceTest {

  @Mock
  UserMapper userMapper;

  @Autowired
  AppUserDetailsService appUserDetailsService;

  @Test
  public void testLoadUserByUsername() {
    // Given
    String name = "Sunny";

    // When
    AppUser appUser = appUserDetailsService.loadAppUserByUserName(name);

    // Then
    assertEquals(name, appUser.getName());
    assertEquals("USER", appUser.getAuthorities().iterator().next().getRole());

  }
}
