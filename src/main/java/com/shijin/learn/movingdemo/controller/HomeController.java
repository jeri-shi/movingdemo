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

import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;

import com.shijin.learn.movingdemo.adapter.LoginUser;
import com.shijin.learn.movingdemo.adapter.UserListQueryParameters;
import com.shijin.learn.movingdemo.service.UsersService;



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
  private UsersService usersService;
  
  @RequestMapping("/home2")
  public String hello2() {
    return "home2";
  }
  
  @RequestMapping("/")
  public String angularIndex() {
    return "redirect:index.html";
  }
  
  @PostMapping("/user/{id}/upload")
  @ResponseBody
  public String handleFileUpload(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
    LOGGER.debug("id = {}, multiFile = {}", id,  file);
    String imagePath = usersService.store(id, file);
    return imagePath;
  }

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  @ResponseBody
  public LoginUser getUser(@PathVariable long id) throws Exception{
    LOGGER.trace("/user...get " + id);
    return usersService.getUser(id);
  }
  
  @RequestMapping(value="/user", method=RequestMethod.POST)
  @ResponseBody
  public LoginUser addUser(@RequestBody LoginUser user) {
    LOGGER.trace("/user...post  " + user);
    return usersService.addUser(user);
  }
  
  @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public LoginUser updateUser(@PathVariable int id, @RequestBody LoginUser user) {
    LOGGER.debug("update a user:{}", user);
    return usersService.updateUser(user);
  }
  
  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public Integer deleteUser(@PathVariable long id) {
    LOGGER.debug("delete a user:{}", id);

    Integer count = usersService.deleteUser(id);
    return count;
  }
  
  @RequestMapping(value="/userslist", method=RequestMethod.POST) 
  @ResponseBody
  public Collection<LoginUser> getUsersList(@RequestBody(required=false) UserListQueryParameters param) {
    LOGGER.trace("/userslist ..." + param);
    return usersService.getUserList(param);
  }

  @RequestMapping(value="/userslistcount", method=RequestMethod.POST) 
  @ResponseBody
  public Long getUsersListCount(@RequestBody(required=false) UserListQueryParameters param) {
    LOGGER.trace("/userslistCount ..." + param);
    return usersService.getUserListCount(param);
  }
  
  @RequestMapping({"/home"}) // Tomcat will forward / to /index, or maybe index.html, index.jsp, default.html
  public String helloWorld(Model model, HttpServletRequest request) {
    LOGGER.trace("/home or / -> helloWorld..." + request.getLocale()); //$NON-NLS-1$
    
    HttpSession session = request.getSession();
    Enumeration<String> en = session.getAttributeNames();
    String item = null;
    while(en.hasMoreElements()) {
      item = en.nextElement();
      LOGGER.debug("key=" + item + ", value=" + session.getAttribute(item));
    }
    
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    LOGGER.trace("RequestContextHolder.getRequestAttributes() = " + requestAttributes);
    if (requestAttributes != null) {
      DefaultOAuth2ClientContext context = (DefaultOAuth2ClientContext) requestAttributes
          .getAttribute("scopedTarget.oauth2ClientContext", RequestAttributes.SCOPE_SESSION);
      if (context == null) {
        LOGGER.trace("scopedTarget.oauth2ClientContext = " + context);
      } else {
        LOGGER.trace("Current accessToken = " + context.getAccessToken());
        LOGGER.trace("Current accessTokenRequest = " + context.getAccessTokenRequest());
      }
    }

    
    LOGGER.debug("session Locale:" + session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE"));
    LOGGER.debug("request Locale:" + request.getLocale());
    LOGGER.debug("localeResolver:" + localResolver.resolveLocale(request));
    session.setAttribute("radio", message.getMessage("HomeController.radio", null, localResolver.resolveLocale(request))); //$NON-NLS-2$
    session.setAttribute("sessionUserName", getLoginUserName()); //$NON-NLS-1$
    
    
//    String userString = restTemplate.getForEntity("http://MOVINGDEMO-USERS/client/user/{1}", String.class, getLoginUserId()).getBody();
    //String userString = usersService.getUser(getLoginUserId());
    String userString = usersService.getUserList(null).toString();
    
    LOGGER.debug("USER_SERVICE.getUser()={}", userString);
    session.setAttribute("userString", userString);

    LOGGER.debug("Login with " + session.getAttribute("sessionUserName") + ", session=" + session.getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    return "redirect:index.html"; //$NON-NLS-1$
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

  @RequestMapping(path = "/me")
  @ResponseBody
  public Map<String, String> user(Principal principal) {
    LOGGER.info("/me -> " + (principal instanceof OAuth2Authentication));
    if (principal instanceof OAuth2Authentication) {
       LOGGER.info("username=" + ((OAuth2Authentication)principal).getName());
    }
    LOGGER.info(principal);
    
    Map<String, String> map = new LinkedHashMap<>();
    map.put("name", getLoginUserName());
    return map;
  }

  private Integer getLoginUserId() {
      Integer id = 0;
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof CompanyUserPrincipal) {
        id = ((CompanyUserPrincipal)principal).getId();
      }
      return id;
  }
  
  private String getLoginUserName() {
    String userName = null;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    LOGGER.debug("principal's userName = " +  userName);
    return userName;
  }

}
