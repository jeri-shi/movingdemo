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

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;

import com.shijin.learn.movingdemo.adapter.LoginUser;



/**
 * @author shijin
 *
 */
@Controller
public class HomeController {
  private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

  @Autowired
  private MessageSource message;
  
  @Autowired
  private LocaleResolver localResolver;
  
  @Autowired
  private RestTemplate restTemplate;
  
  @RequestMapping("/home2")
  public String hello2() {
    return "home2";
  }
  
  @RequestMapping({"/home", "/"}) // Tomcat will forward / to /index, or maybe index.html, index.jsp, default.html
  public String helloWorld(Model model, HttpServletRequest request) {
    LOGGER.trace("helloWorld..." + request.getLocale()); //$NON-NLS-1$
    
    HttpSession session = request.getSession();
    Enumeration<String> en = session.getAttributeNames();
    String item = null;
    while(en.hasMoreElements()) {
      item = en.nextElement();
      LOGGER.debug("key=" + item + ", value=" + session.getAttribute(item));
    }
    
    LOGGER.debug("session Locale:" + session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE"));
    LOGGER.debug("request Locale:" + request.getLocale());
    LOGGER.debug("localeResolver:" + localResolver.resolveLocale(request));
    session.setAttribute("radio", message.getMessage("HomeController.radio", null, localResolver.resolveLocale(request))); //$NON-NLS-2$
    session.setAttribute("sessionUserName", getLoginUserName()); //$NON-NLS-1$
    
    
    String userString = restTemplate.getForEntity("http://MOVINGDEMO-USERS/user/{1}", String.class, 33).getBody();
    LOGGER.debug("USER_SERVICE.getUser()={}", userString);
    
    
    LOGGER.debug("Login with " + session.getAttribute("sessionUserName") + ", session=" + session.getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    return "home"; //$NON-NLS-1$
  }

  @GetMapping("/login")
  public String homePage(@ModelAttribute("loginUser") LoginUser loginUser, BindingResult result,
      @RequestParam(required = false) String error, HttpServletRequest request) {
    LOGGER.trace("/login with parameter=" + error); //$NON-NLS-1$
    if (error != null) {
      result.addError(new ObjectError("username", message.getMessage("HomeController.login.error", null, localResolver.resolveLocale(request)))); //$NON-NLS-1$ //$NON-NLS-2$
      result.addError(new ObjectError("password", "")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    return "index"; //$NON-NLS-1$
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
