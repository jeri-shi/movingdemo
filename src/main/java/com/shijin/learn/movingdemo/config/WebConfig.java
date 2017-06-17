package com.shijin.learn.movingdemo.config;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

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
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
  
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/").setViewName("index");
    LOGGER.trace("addViewControllers...");
  }
  
  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    LOGGER.trace("configureViewResolvers...");
    registry.jsp("/WEB-INF/jsp/", ".jsp");
  }

  @Bean
  public MessageSource  messageSource() {
    ReloadableResourceBundleMessageSource message = new ReloadableResourceBundleMessageSource();
    message.setBasename("/WEB-INF/messages/messages");
    return message;
  }
  
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    return localeChangeInterceptor;
  }
  
  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(Locale.ENGLISH);
    return localeResolver;
  }
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
    registry.addInterceptor(themeChangeInterceptor());
  }
  
  @Bean
  public ResourceBundleThemeSource themeSource() {
    ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
    return themeSource;
  }
  
  @Bean
  public SessionThemeResolver themeResolver() {
    SessionThemeResolver themeResolver = new SessionThemeResolver();
    themeResolver.setDefaultThemeName("green");
    return themeResolver;
  }
  
  @Bean
  public ThemeChangeInterceptor themeChangeInterceptor() {
    ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
    themeChangeInterceptor.setParamName("theme");
    return themeChangeInterceptor;
  }
}
