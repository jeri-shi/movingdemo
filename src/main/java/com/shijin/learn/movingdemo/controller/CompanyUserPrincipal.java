/**
 * 
 */
package com.shijin.learn.movingdemo.controller;

import java.io.Serializable;

/**
 * @author shijin
 *
 */
public class CompanyUserPrincipal implements Serializable {
  private static final long serialVersionUID = 5448575809346946875L;

  private String username;
  private String company;

  public CompanyUserPrincipal(String company, String username) {
    this.username = username;
    this.company = company;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "<" + company + ", " + username + ">";
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;

    if (!(o instanceof CompanyUserPrincipal)) {
      return false;
    }

    CompanyUserPrincipal test = (CompanyUserPrincipal) o;
    boolean isCompanyEquals = false;
    boolean isUsernameEquals = false;

    if (company != null) {
      isCompanyEquals = company.equals(test.getCompany());
    } else if (test.getCompany() == null) {
      isCompanyEquals = true;
    }

    if (!isCompanyEquals)
      return false;

    if (username != null) {
      isUsernameEquals = username.equals(test.getUsername());
    } else if (test.getUsername() == null) {
      isUsernameEquals = true;
    }

    return isCompanyEquals && isUsernameEquals;
  }
}
