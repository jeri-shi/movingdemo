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

package com.shijin.learn.movingdemo.adapter;

import java.io.Serializable;

/**
 * @author shijin
 *
 */
public class LoginUser implements Serializable {
  private static final long serialVersionUID = 5690368878446330939L;
  private Long id;
  private String company;
  private String username;
  private String password;
  private String roles;
  private Boolean enabled;
  private Boolean expired;
  
  
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean getExpired() {
    return expired;
  }

  public void setExpired(Boolean expired) {
    this.expired = expired;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  /**
   * @return the name
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param name the name to set
   */
  public void setUsername(String name) {
    this.username = name;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String toString() {
    return 
      new StringBuffer()
        .append("LoginUser{")
        .append("id:").append(id)
        .append(",company:").append(company)
        .append(",name:").append(username)
        .append(",roles:").append(roles)
        .append(",enabled:").append(this.enabled)
        .append(",expired:").append(this.expired)
        .append("}")
        .toString();
  }
}
