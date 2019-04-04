package com.sgcai.boot.api.bootapi.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sgcai.boot.api.bootapi.utils.Constants;
import com.sgcai.boot.api.bootapi.utils.CookieUtil;
import com.sgcai.boot.api.bootapi.wrapper.WebRequestWrapper;
import com.sgcai.boot.service.UserService;
import com.sgcai.boot.to.UserTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OnlineUserInterceptor implements HandlerInterceptor {

    @Reference(version = "1.0.0")
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineUserInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse res, Object handler) throws Exception {
        LOGGER.info("#####################进入onlineUser拦截器###############");
        WebRequestWrapper req = getRequestWrapper(request);
        Cookie cookie = CookieUtil.getCookieByName(req, Constants.SESSION_LOGIN_USER);
        if (cookie != null) {
            UserTO user = userService.findUserByToken(cookie.getValue());
            req.setPrincipal(user);
        }
        return true;
    }


    private WebRequestWrapper getRequestWrapper(HttpServletRequest request) {
        if (request instanceof DefaultMultipartHttpServletRequest) {
            DefaultMultipartHttpServletRequest multipartHttpServletRequest = (DefaultMultipartHttpServletRequest) request;
            HttpServletRequest req = multipartHttpServletRequest.getRequest();
            return (WebRequestWrapper) req;
        }
        return (WebRequestWrapper) request;
    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
