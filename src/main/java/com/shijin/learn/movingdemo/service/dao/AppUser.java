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

package com.shijin.learn.movingdemo.service.dao;

import java.util.Collection;

/**
 * @author shijin
 *
 */
public class AppUser {
  private String name;
  private String pwd;
  private Collection<Authority> authorities;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the pwd
   */
  public String getPwd() {
    return pwd;
  }

  /**
   * @param pwd the pwd to set
   */
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  /**
   * @return the authorities
   */
  public Collection<Authority> getAuthorities() {
    return authorities;
  }

  /**
   * @param authorities the authorities to set
   */
  public void setAuthorities(Collection<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (authorities != null) {
      for (Authority role : authorities) {
        builder.append(role.getRole());
        builder.append(",");
      }
    }
    return name + ",pwd[" + pwd + "],role[" + builder.toString() + "]";
  }
}
