package com.shijin.learn.movingdemo.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JavaTest {

  @Test
  public void stringTest() {
    String strA = "aaa";
    String strB = null;
    
    assertFalse(strA.equals(strB));
    assertTrue("aaa".equals(strA));
    assertFalse("aaa".equals(strB));
    
  }
}
