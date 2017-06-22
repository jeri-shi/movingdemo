/**
 * 
 */
package com.shijin.learn.movingdemo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author shijin
 *
 */
public class CompanyUserPwdProcessingFilter extends AbstractAuthenticationProcessingFilter {
  private static final Logger LOGGER = LogManager.getLogger(CompanyUserPwdProcessingFilter.class);
  public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
  public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
  public static final String SPRING_SECURITY_FORM_COMPANY_KEY = "company";

  private String companyParameter = SPRING_SECURITY_FORM_COMPANY_KEY;
  private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
  private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

  private boolean postOnly = true;

  public CompanyUserPwdProcessingFilter() {
    super(new AntPathRequestMatcher("/login", "POST"));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#
   * attemptAuthentication(javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse)
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    
    if (postOnly && !request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }
    String company = getParameter(request, companyParameter);
    String username = getParameter(request, usernameParameter);
    String password = getParameter(request, passwordParameter);

    CompanyUserPrincipal user = new CompanyUserPrincipal(company, username);
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(user, password);
    LOGGER.debug("token=" + token);
    return this.getAuthenticationManager().authenticate(token);
  }

  private String getParameter(HttpServletRequest request, String para) {
    String attribute = (String) request.getParameter(para);
    return (attribute == null) ? "" : attribute;
  }

  public String getUsernameParameter() {
    return usernameParameter;
  }

  public void setUsernameParameter(String usernameParameter) {
    this.usernameParameter = usernameParameter;
  }

  public String getPasswordParameter() {
    return passwordParameter;
  }

  public void setPasswordParameter(String passwordParameter) {
    this.passwordParameter = passwordParameter;
  }

  public String getCompanyParameter() {
    return companyParameter;
  }

  public void setCompanyParameter(String companyParameter) {
    this.companyParameter = companyParameter;
  }

}
