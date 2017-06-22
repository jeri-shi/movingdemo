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

package com.shijin.learn.movingdemo.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shijin.learn.movingdemo.config.DebugConfig;
import com.shijin.learn.movingdemo.config.HttpSessionConfig;
import com.shijin.learn.movingdemo.config.RootConfig;
import com.shijin.learn.movingdemo.config.SecurityConfig;
import com.shijin.learn.movingdemo.config.WebConfig;

/**
 * @author shijin
 *
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, DebugConfig.class, WebConfig.class,
    SecurityConfig.class, HttpSessionConfig.class})
@ActiveProfiles("dev")
public class HomeControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
  }

  @Test
  public void testHello2() throws Exception {
    this.mockMvc.perform(get("/home2")).andDo(print()).andExpect(status().isOk())
        .andExpect(forwardedUrl("/WEB-INF/jsp/home2.jsp"));
  }

  @Test
  @WithMockUser(username = "Jeri", roles = "USER")
  public void testHelloWorld() throws Exception {
    this.mockMvc.perform(get("/home").locale(Locale.ENGLISH)).andDo(print())
        .andExpect(forwardedUrl("/WEB-INF/jsp/home.jsp"))
        .andExpect(request().sessionAttribute("radio", "On The Air"))
        .andExpect(request().sessionAttribute("sessionUserName", "Jeri"));
  }

  @Test
  public void testLoginFormSuccess() throws Exception{
      //Given an Account in InMemoryAuthentication
      mockMvc.perform(formLogin().user("ShiJin").password("111111"))
        .andDo(print())
        .andExpect(authenticated().withRoles("USER"));

      //Given an Account in InMemoryDB
      mockMvc.perform(formLogin().user("Jeri").password("111111"))
      .andDo(print())
      .andExpect(authenticated().withUsername("Jeri").withRoles("USER","ADMIN"));
  }

  @Test
  public void testLoginFormFailure() throws Exception {
    mockMvc.perform(formLogin().user("Jeri Shi").password("111111"))
    .andDo(print())
    .andExpect(unauthenticated());
     
  }
  
  @Test
  public void testHomePage() throws Exception {
    this.mockMvc.perform(get("/login")).andDo(print())
        .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));

    this.mockMvc.perform(get("/login").param("error", "")).andDo(print());
  }

  @Test
  public void testLogOut() throws Exception {
    this.mockMvc.perform(logout()).andDo(print()).andExpect(unauthenticated());
  }
}
