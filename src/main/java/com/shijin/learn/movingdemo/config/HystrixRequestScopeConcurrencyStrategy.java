/**
 * 
 */
package com.shijin.learn.movingdemo.config;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

/**
 * @author shijin
 *
 */
public class HystrixRequestScopeConcurrencyStrategy extends HystrixConcurrencyStrategy {
  private static final Logger LOGGER = LogManager.getLogger(HystrixRequestScopeConcurrencyStrategy.class);

  @Autowired
  OAuth2ClientContext oauth2ClientContext;

  @Override
  public <T> Callable<T> wrapCallable(Callable<T> callable) {

    // retrieves current ThreadLocal request attributes
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    LOGGER.trace("RequestContextHolder.getRequestAttributes() = " + requestAttributes);
    if (requestAttributes != null) {
      DefaultOAuth2ClientContext context = (DefaultOAuth2ClientContext) requestAttributes
          .getAttribute("scopedTarget.oauth2ClientContext", RequestAttributes.SCOPE_SESSION);
      if (context == null) {
        LOGGER.trace("scopedTarget.oauth2ClientContext = " + context);
      } else {
        LOGGER.trace("Current accessToken = " + context.getAccessToken());
        LOGGER.trace("Current accessTokenRequest = " + context.getAccessTokenRequest());
      }
    }
    SecurityContext securityContext = SecurityContextHolder.getContext();
    LOGGER.debug("securityContext=" + securityContext);
    return new WrapperCallable<T>(requestAttributes, securityContext, callable);
  }

  //
  private static class WrapperCallable<T> implements Callable<T> {
    private SecurityContext securityContext;
    private RequestAttributes requestAttributes;
    private Callable<T> target;

    WrapperCallable(RequestAttributes requestAttributes, SecurityContext securityContext,
        Callable<T> target) {
      this.securityContext = securityContext;
      this.requestAttributes = requestAttributes;
      this.target = target;
    }

    @Override
    public T call() throws Exception {
      RequestContextHolder.setRequestAttributes(this.requestAttributes);
      SecurityContextHolder.setContext(securityContext);
      try {
        return target.call();
      } finally {
        RequestContextHolder.resetRequestAttributes();
      }
    }
  }
}
