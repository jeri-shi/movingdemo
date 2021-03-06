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

package com.shijin.learn.movingdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shijin.learn.movingdemo.controller.CompanyUserPwdProcessingFilter;
import com.shijin.learn.movingdemo.service.CompanyUserAuthenticationProvider;

/**
 * @author shijin
 *
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DaoAuthenticationProvider daoInMemoryProvider;

  @Autowired
  private AuthenticationManager myAuthenticationManager;

  @Autowired
  private DaoAuthenticationProvider myBatisProvider;
  
  @Autowired
  CompanyUserAuthenticationProvider companyUserAuthenticationProvider;

  // When implement a customized UserDetailsService, below method must be comments to make
  // customized one to work
  //
  // @Bean
  // public UserDetailsService userDetailsService() {
  // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
  // manager.createUser(User.withUsername("ShiJin").password("111111").roles("USER").build());
  // return manager;
  // }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    super.configure(auth);
//    auth.
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // http.authorizeRequests().anyRequest().permitAll();
    http
      .authenticationProvider(daoInMemoryProvider)
      .authenticationProvider(myBatisProvider)
      .authenticationProvider(companyUserAuthenticationProvider);

    http.authorizeRequests()
          .antMatchers("/static/**").permitAll()
          .antMatchers("/home2").permitAll()
          .antMatchers("/login**").permitAll()
          .anyRequest().authenticated()
        .and()
          .exceptionHandling()
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        .and()
          .formLogin()
            .loginPage("/login").permitAll()
            .failureUrl("/login?error").permitAll()
            .defaultSuccessUrl("/home")
        .and()
          .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout").permitAll()
        .and()
          .addFilterBefore(companyUserPwdProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
          ;
        

    // .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // /logout is post method by
    // default.


  }

  @Bean(name = "myAuthenticationManager")
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public CompanyUserPwdProcessingFilter companyUserPwdProcessingFilter() throws Exception {
    CompanyUserPwdProcessingFilter filter = new CompanyUserPwdProcessingFilter();
    filter.setAuthenticationManager(myAuthenticationManager);
    filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(
                "/login?error"));
    SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
    handler.setDefaultTargetUrl("/home");
    filter.setAuthenticationSuccessHandler(handler);
    return filter;
  }
}
