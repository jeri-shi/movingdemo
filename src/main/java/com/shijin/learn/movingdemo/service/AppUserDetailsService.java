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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.shijin.learn.movingdemo.service.dao.AppCompanyUser;
import com.shijin.learn.movingdemo.service.dao.AppUser;
import com.shijin.learn.movingdemo.service.dao.converter.ConvertAppUser;
import com.shijin.learn.movingdemo.service.mapper.UserMapper;

/**
 * @author shijin
 *
 */
@Service
public class AppUserDetailsService implements UserDetailsService{
  private static final Logger LOGGER = LogManager.getLogger(AppUserDetailsService.class);
  
  @Autowired
  private UserMapper userMapper;
  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.
   * String)
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Assert.notNull(username, "username cannot be null.");

    AppUser appUser = userMapper.loadAppUserByUserName(username);
    LOGGER.debug("retrive AppUser from service layer: " + appUser);
    if (appUser == null) {
      throw new UsernameNotFoundException(username);
    }
    return ConvertAppUser.convert(appUser);
  }

  public AppCompanyUser loadUserByCompanyUsername(String company, String username) throws UsernameNotFoundException {
    Assert.notNull(company, "company cannot be null");
    Assert.notNull(username, "username cannot be null");
    
    AppCompanyUser user = userMapper.loadAppUserByCompanyUserName(company, username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    return user;
  }
}
