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

package com.shijin.learn.movingdemo.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shijin.learn.movingdemo.service.dao.AppCompanyUser;
import com.shijin.learn.movingdemo.service.dao.AppUser;

/**
 * @author shijin
 *
 */
@Mapper
public interface UserMapper {

  AppUser loadAppUserByUserName(String name);
  AppCompanyUser loadAppUserByCompanyUserName(@Param("company") String company, @Param("name") String name);
}
