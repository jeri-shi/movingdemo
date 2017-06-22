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

package com.shijin.learn.movingdemo.service.dao.converter;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.shijin.learn.movingdemo.controller.CompanyUserPrincipal;
import com.shijin.learn.movingdemo.service.dao.AppCompanyUser;
import com.shijin.learn.movingdemo.service.dao.AppUser;

/**
 * @author shijin
 *
 */

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ConvertAppUserBetweenUserDetailsTest {
  private static final Logger LOGGER =
      LogManager.getLogger(ConvertAppUserBetweenUserDetailsTest.class);

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testConvert_AppCompanyUser_isNull() {
    AppCompanyUser user = null;
    thrown.expect(IllegalArgumentException.class);

    ConvertAppUser.convert(user);

  }

  @Test
  public void testConvert_AppUser_is_Null() {
    AppUser appUser = null;
    // Given appUser is null
    thrown.expect(IllegalArgumentException.class);
    // When
    ConvertAppUser.convert(appUser);
    LOGGER.info("indicating......33"); // start from here, no log output, because exception was
                                       // thrown.
  }

  @Test
  public void testConvert_AppUser_Roles_is_Null() {
    AppUser appUser = new AppUser();
    // Given appUser is null
    thrown.expect(IllegalArgumentException.class);
    // When
    ConvertAppUser.convert(appUser);
  }

  @Test
  public void testConvert_AppUser_One_Role() {
    // Given a integral AppUser
    String name = "ShiJin";
    String password = "123456";
    String userRole = "USER";
    convert_AppUser_Roles(name, password, userRole);
  }

  @Test
  public void testConvert_AppUser_Two_Roles() {
    // Given a integral AppUser
    String name = "ShiJin";
    String password = "123456";
    String userRole = "USER";
    String adminRole = "ADMIN";
    convert_AppUser_Roles(name, password, userRole, adminRole);
  }

  @Test
  public void testConvert_AppCompanyUser_TwoRoles() {
    String company = "Learn";
    String name = "ShiJin";
    String password = "123456";
    String userRole = "USER";
    String adminRole = "ADMIN";
    AppUser appUser = Utility.getAppUser(name, password, userRole, adminRole);

    AppCompanyUser appCompanyUser = new AppCompanyUser();
    appCompanyUser.setCompany(company);
    appCompanyUser.setName(name);
    appCompanyUser.setPwd(password);
    appCompanyUser.setAuthorities(appUser.getAuthorities());

    UsernamePasswordAuthenticationToken token = ConvertAppUser.convert(appCompanyUser);

    Assert.isInstanceOf(CompanyUserPrincipal.class, token.getPrincipal());
    assertEquals(company, ((CompanyUserPrincipal) token.getPrincipal()).getCompany());
    assertEquals(name, ((CompanyUserPrincipal) token.getPrincipal()).getUsername());
  }

  @Test
  public void testConvert_AppUser_Two_Roles_StartWith_ROLE_() {
    // Given a integral AppUser
    String name = "ShiJin";
    String password = "123456";
    String userRole = "ROLE_USER";
    String adminRole = "ROLE_ADMIN";
    AppUser appUser = Utility.getAppUser(name, password, userRole, adminRole);
    thrown.expect(IllegalArgumentException.class);
    // When
    ConvertAppUser.convert(appUser);
  }

  protected void convert_AppUser_Roles(String name, String password, String... roles) {
    AppUser appUser = Utility.getAppUser(name, password, roles);

    // When
    UserDetails userDetails = ConvertAppUser.convert(appUser);

    // Then
    assertEquals(name, userDetails.getUsername());
    assertEquals(password, userDetails.getPassword());
    assertEquals(roles.length, userDetails.getAuthorities().size());

    Utility.assertEquals(roles.clone(), userDetails.getAuthorities());
    
    LOGGER.debug("testConvert_AppUser_Two_Roles is passed.");
  }



}
