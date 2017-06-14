/**
 * Copyright 2017 Shi Jin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shijin.learn.movingdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author shijin
 *
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

  @Bean
  public LettuceConnectionFactory connectionFactory() {
    return new LettuceConnectionFactory();
  }
  
  /**
   * httpSessionEventPublisher convert Http Event to Spring application event
   * @return
   */
  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }
  
  /**
   * Once create ApplicationEvent listener implementation, it will automatically monitor spring event.
   * @return
   */
  @Bean
  public SessionCreatedListener sessionCreatedListener() {
    return new SessionCreatedListener();
  }

  @Bean
  public SessionDestroyedListener sessionDestroyedListener() {
    return new SessionDestroyedListener();
  }

  /**
   * Used for Restful, use http header instead of cookie
   * @return
   */
//  @Bean
//  public HttpSessionStrategy httpSessionStrategy() {
//    return new HeaderHttpSessionStrategy();
//  }
}
