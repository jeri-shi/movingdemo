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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.shijin.learn.movingdemo.service.dao.AppUser;
import com.shijin.learn.movingdemo.service.dao.Authority;

/**
 * @author shijin
 *
 */
public class ConvertAppUserBetweenUserDetails {

  public static UserDetails convert(AppUser appUser) {

    return User.withUsername(appUser.getName()).password(appUser.getPwd())
        .authorities(roles(appUser.getAuthorities())).build();

  }

  private static List<GrantedAuthority> roles(Collection<Authority> roles) {
    Assert.notNull(roles, "roles cannot be null");
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.size());
    for (Authority role : roles) {
      Assert.isTrue(!role.getRole().startsWith("ROLE_"),
          role.getRole() + " cannot start with ROLE_ (it is automatically added)");
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
    }
    return authorities;
  }

}
