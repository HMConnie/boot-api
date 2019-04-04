package com.sgcai.boot.api.bootapi.interceptor;

import com.sgcai.boot.api.bootapi.anno.NeedLogin;
import com.sgcai.boot.api.bootapi.exception.AuthException;
import com.sgcai.boot.api.bootapi.wrapper.WebRequestWrapper;
import com.sgcai.boot.to.UserTO;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tong on 16/10/25.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NeedLogin needLogin = handlerMethod.getMethodAnnotation(NeedLogin.class);
        if (needLogin == null) {
            return true;
        }
        WebRequestWrapper wrapper = (WebRequestWrapper) req;
        UserTO user = (UserTO) wrapper.getUserPrincipal();
        if (user == null) {
            throw new AuthException();
        }
        return super.preHandle(req, res, handler);
    }
}
