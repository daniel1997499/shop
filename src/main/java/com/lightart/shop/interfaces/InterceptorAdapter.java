package com.lightart.shop.interfaces;

import com.lightart.shop.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InterceptorAdapter implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(MainController.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpServletRequest requestCacheWrapperObject
                = new ContentCachingRequestWrapper(request);
        requestCacheWrapperObject.getParameterMap();
        log.info("Page: " + requestCacheWrapperObject.getRequestURL() + " accessed by: " + requestCacheWrapperObject.getRemoteAddr() + " with locale: " + requestCacheWrapperObject.getLocale());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //
    }
}

