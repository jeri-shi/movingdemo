package com.shijin.learn.movingdemo.service.dao;

public class AppCompanyUser extends AppUser {
  private String company;
  private boolean enabled;

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }
  
  @Override
  public String toString() {
    return company + ", " + super.toString();
  }
  
  @Override
  public boolean equals(Object test) {
    if (test == null) return false;
    
    if (!(test instanceof AppCompanyUser)) return false;
    
    AppCompanyUser testUser = (AppCompanyUser)test;
    return company.equals(testUser.getCompany()) && getName().equals(testUser.getName());
  }
}
