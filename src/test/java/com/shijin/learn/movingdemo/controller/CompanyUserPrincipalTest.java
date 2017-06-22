package com.shijin.learn.movingdemo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;


public class CompanyUserPrincipalTest {

  @Test
  public void testEquals() {
    CompanyUserPrincipal object = new CompanyUserPrincipal(null, null);
    CompanyUserPrincipal test = new CompanyUserPrincipal(null, null);
    assertEquals("should be equals with null", object, test);

    object.setCompany("company");
    test.setCompany("company");
    assertEquals("should be equals with both username=null", object, test);

    object.setUsername("username");
    test.setUsername("username");
    assertEquals("should be equals with not null", object, test);

    object.setCompany(null);
    test.setCompany("company");
    object.setUsername("username");
    test.setUsername("username");
    assertNotEquals("should not be equals with company is null", object, test);

    object.setCompany("company");
    test.setCompany(null);
    object.setUsername("username");
    test.setUsername("username");
    assertNotEquals("should not be equals with test.company is null", object, test);

    object.setCompany("company");
    test.setCompany("company");
    object.setUsername(null);
    test.setUsername("username");
    assertNotEquals("should not be equals with username is null", object, test);

    object.setCompany("company");
    test.setCompany("company");
    object.setUsername("username");
    test.setUsername(null);
    assertNotEquals("should not be equals with test.username is null", object, test);

    assertNotEquals("should not be equals with null object", object, null);
  }
}
