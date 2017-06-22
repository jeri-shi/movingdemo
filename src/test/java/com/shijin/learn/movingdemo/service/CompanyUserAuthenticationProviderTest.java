/**
 * 
 */
package com.shijin.learn.movingdemo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.util.Assert.isInstanceOf;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.shijin.learn.movingdemo.config.DebugConfig;
import com.shijin.learn.movingdemo.config.RootConfig;
import com.shijin.learn.movingdemo.controller.CompanyUserPrincipal;
import com.shijin.learn.movingdemo.service.dao.AppCompanyUser;
import com.shijin.learn.movingdemo.service.dao.converter.Utility;

/**
 * @author shijin
 *
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DebugConfig.class})
@ActiveProfiles("dev")
public class CompanyUserAuthenticationProviderTest {
  @Mock
  AppUserDetailsService appUserDetailsService;

  @Autowired
  @InjectMocks
  CompanyUserAuthenticationProvider companyUserAuthenticationProvider;
  
  @Mock
  Authentication authentication;
  
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testAuthenticate() {
    //Given
    String username = "ShiJin";
    String company = "Learn";
    CompanyUserPrincipal principal = new CompanyUserPrincipal(company, username);
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, "111");
    AppCompanyUser userDetails = new AppCompanyUser();
    userDetails.setCompany(company);
    userDetails.setName(username);
    userDetails.setPwd("111");
    userDetails.setAuthorities(Utility.convertRoles("USER", "ADMIN"));
    given(appUserDetailsService.loadUserByCompanyUsername(company, username)).willReturn(userDetails);
    
    //When
    Authentication result = companyUserAuthenticationProvider.authenticate(authentication);
    
    //Then
    Assert.isInstanceOf(Authentication.class, result);
    isInstanceOf(CompanyUserPrincipal.class, result.getPrincipal());
    assertEquals(company, ((CompanyUserPrincipal)result.getPrincipal()).getCompany());
    assertEquals("111", result.getCredentials());
    assertEquals(username, ((CompanyUserPrincipal)result.getPrincipal()).getUsername());
    Utility.assertEquals(new String[]{"ADMIN","USER"}, result.getAuthorities());
  }
  
  
}
