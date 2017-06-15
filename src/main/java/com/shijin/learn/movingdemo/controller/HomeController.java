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

package com.shijin.learn.movingdemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shijin.learn.movingdemo.adapter.LoginUser;

/**
 * @author shijin
 *
 */
@Controller
public class HomeController {
  private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

  @RequestMapping("/home2")
  public String hello2() {
    return "home2";
  }
  
  @RequestMapping({"/home", "/"}) // Tomcat will forward / to /index, or maybe index.html, index.jsp, default.html
  public String helloWorld(Model model, HttpServletRequest request) {
    LOGGER.trace("helloWorld...");
    
    HttpSession session = request.getSession();
    session.setAttribute("radio", "On The Air");
    session.setAttribute("sessionUserName", getLoginUserName());

    LOGGER.debug("Login with " + session.getAttribute("sessionUserName") + ", session=" + session.getId());

    return "home";
  }

  @GetMapping("/login")
  public String homePage(@ModelAttribute("loginUser") LoginUser loginUser, BindingResult result,
      @RequestParam(required = false) String error) {
    LOGGER.trace("/login with parameter=" + error);
    if (error != null) {
      result.addError(new ObjectError("username", "User Name or Password is not right."));
      result.addError(new ObjectError("password", ""));
    }

    return "index";
  }

  private String getLoginUserName() {
    String userName = null;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    return userName;
  }

}
