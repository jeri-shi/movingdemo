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
import org.springframework.security.web.session.HttpSessionDestroyedEvent;

/**
 * @author shijin
 *
 */
public class SessionDestroyedListener implements ApplicationListener<HttpSessionDestroyedEvent> {
  private static final Logger LOGGER = LogManager.getLogger(SessionDestroyedListener.class);

  @Override
  public void onApplicationEvent(HttpSessionDestroyedEvent event) {
    LOGGER.info("Session [" + event.getSession().getId() + "] was destroyed...");

  }

}
