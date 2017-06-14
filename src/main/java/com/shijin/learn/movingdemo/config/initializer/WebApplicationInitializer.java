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
package com.shijin.learn.movingdemo.config.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.shijin.learn.movingdemo.config.HttpSessionConfig;
import com.shijin.learn.movingdemo.config.RootConfig;
import com.shijin.learn.movingdemo.config.SecurityConfig;
import com.shijin.learn.movingdemo.config.WebConfig;

/**
 * @author shijin
 *
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  private static final Logger LOGGER = LogManager.getLogger(WebApplicationInitializer.class);
  /*
   * Service/Repositories configuration class
   * 
   * @see
   * org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#
   * getRootConfigClasses()
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    LOGGER.trace("getRootConfigClasses...");
    return new Class[] {RootConfig.class, SecurityConfig.class, HttpSessionConfig.class};
  }

  /*
   * Controllers/ViewResovlers/HanlderMappings configuration class
   * 
   * @see
   * org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#
   * getServletConfigClasses()
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    LOGGER.trace("getServletConfigClasses...");
    return new Class[] {WebConfig.class};
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings
   * ()
   */
  @Override
  protected String[] getServletMappings() {
    LOGGER.trace("getServletMappings...");
    return new String[] {"/"};
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    servletContext.addListener(HttpSessionEventPublisher.class);
    
  }
}
