/**
 * 
 */
package com.shijin.learn.movingdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.shijin.learn.movingdemo.controller.CompanyUserPrincipal;
import com.shijin.learn.movingdemo.service.dao.AppCompanyUser;
import com.shijin.learn.movingdemo.service.dao.converter.ConvertAppUser;

/**
 * @author shijin
 *
 */
@Service
public class CompanyUserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  @Autowired
  private AppUserDetailsService appUserDetailsService;

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#
   * additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails,
   * org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
   */
  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#
   * retrieveUser(java.lang.String,
   * org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
   */
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
        messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports",
            "Only UsernamePasswordAuthenticationToken is supported"));
    
    Object o = authentication.getPrincipal();
    if (!(o instanceof CompanyUserPrincipal)) {
      throw new ProviderNotFoundException(
          "authentication.getPrincipal() is not type of CompanyUserPrincipal");
    }

    CompanyUserPrincipal principal = (CompanyUserPrincipal) o;
    AppCompanyUser user =
        appUserDetailsService.loadUserByCompanyUsername(principal.getCompany(), principal.getUsername());
    if (user == null) {
      throw new InternalAuthenticationServiceException(
          "UserDetailsService returned null, which is an interface contract violation");
    }
    
    if (!user.getPwd().equals(authentication.getCredentials())) {
      throw new BadCredentialsException("Authentication is failed");
    }
    
    return ConvertAppUser.convert(user);
  }

  @Override
  protected UserDetails retrieveUser(String username,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    //won't be invoked
    return null;
  }

}
