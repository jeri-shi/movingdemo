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

package com.shijin.learn.movingdemo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shijin
 *
 */
@Controller
public class HomeController {
  private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
  @RequestMapping("/hello")
  public String helloWorld() {
    LOGGER.trace("helloWorld...");
    return "home";
  }
}
