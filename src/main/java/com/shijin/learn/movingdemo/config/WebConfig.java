package com.shijin.learn.movingdemo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.shijin.learn.movingdemo.controller")
public class WebConfig extends WebMvcConfigurerAdapter{
  private static final Logger LOGGER = LogManager.getLogger(WebConfig.class);
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//    registry.addResourceHandler("/index.html").addResourceLocations("/");
    LOGGER.trace("addResourceHandlers...");
    //the code below enable accessing to /home.html and /index.html although they are not in same folders.
    //registry.addResourceHandler("/**.html").addResourceLocations("/", "/static/");
  }
  
  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    
  }
  
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/login").setViewName("index");
    LOGGER.trace("addViewControllers...");
  }
  
  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    LOGGER.trace("configureViewResolvers...");
    registry.jsp("/WEB-INF/jsp/", ".jsp");
  }
  
}
