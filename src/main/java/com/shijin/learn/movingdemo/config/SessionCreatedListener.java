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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;

/**
 * @author shijin
 *
 */
public class SessionCreatedListener implements ApplicationListener<HttpSessionCreatedEvent> {
  private static final Logger LOGGER = LogManager.getLogger(SessionCreatedListener.class);

  @Override
  public void onApplicationEvent(HttpSessionCreatedEvent event) {
    LOGGER.trace("new Session [" + event.getSession().getId() + "] is created...");
    //LOGGER.info("new Session's source {}", event.getSource());
    // set session invalid time to 50 seconds
    event.getSession().setMaxInactiveInterval(600);

  }

}
