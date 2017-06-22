package com.shijin.learn.movingdemo.service.dao.converter;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.springframework.security.core.GrantedAuthority;

import com.shijin.learn.movingdemo.service.dao.AppUser;
import com.shijin.learn.movingdemo.service.dao.Authority;

public class Utility {

  public static Collection<Authority> convertRoles(String... roles) {
    Authority authority = null;
    Collection<Authority> list = new ArrayList<Authority>(roles.length);
    for (String role : roles) {
      authority = new Authority();
      authority.setRole(role);
      list.add(authority);
    }
    
    return list;
  }

  public static AppUser getAppUser(String name, String password, String... roles) {

    AppUser appUser = new AppUser();
    appUser.setName(name);
    appUser.setPwd(password);
    appUser.setAuthorities(Utility.convertRoles(roles));

    return appUser;
  }

  public static boolean assertEquals(String[] expected, Collection<? extends GrantedAuthority> authorities) {
    String strRole = "";
    String strParaRole = "";
    
    int i = expected.length;
    for (GrantedAuthority auth : authorities) {
      strRole += auth.getAuthority();
      strParaRole += ("ROLE_" + expected[--i]);
    }
    
    Assert.assertEquals(strParaRole, strRole);
    return true;
  }
  

}
