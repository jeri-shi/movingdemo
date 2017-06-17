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
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author shijin
 *
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DaoAuthenticationProvider daoInMemoryProvider;
  
  @Autowired
  private DaoAuthenticationProvider myBatisProvider;
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
    // TODO Auto-generated method stub
    super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // http.authorizeRequests().anyRequest().permitAll();

    http.authenticationProvider(daoInMemoryProvider);
    http.authenticationProvider(myBatisProvider);

    http.authorizeRequests()
          .antMatchers("/static/**").permitAll()
          .antMatchers("/home2").permitAll()
          .antMatchers("/login**").permitAll()
          .anyRequest().authenticated()
        .and()
          .formLogin()
            .loginPage("/login").permitAll()
            .failureUrl("/login?error").permitAll()
            .defaultSuccessUrl("/home")
        .and()
          .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout").permitAll();

    // .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // /logout is post method by
    // default.


  }


}
