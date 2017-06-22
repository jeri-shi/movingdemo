package com.shijin.learn.movingdemo.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, DebugConfig.class, WebConfig.class,
    SecurityConfig.class, HttpSessionConfig.class})
@ActiveProfiles("dev")
public class CompanyUserPwdProcessingFilterTest {
  
  @Autowired
  private WebApplicationContext wac;
  
  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
    
  }
  
  @Test
  public void testFilter() throws Exception {
    CompanyUserPrincipal expected = new CompanyUserPrincipal("testCompany", "testUser");
    
    mockMvc.perform(post("/login").with(csrf())
                    .param("company", "testCompany")
                    .param("username", "testUser")
                    .param("password", "testPwd"))
                  .andExpect(
                      unauthenticated()
                   );
    
  }
}
