package com.sgcai.boot.api.bootapi.filter;


import com.sgcai.boot.api.bootapi.wrapper.WebRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebRequestWrapperFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebRequestWrapperFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("#####################进入WebRequestWrapper过滤器##############");
        HttpServletRequest req = (HttpServletRequest) request;
        WebRequestWrapper webRequestWrapper = new WebRequestWrapper(req);
        HttpServletResponse resp = (HttpServletResponse) response;
        chain.doFilter(webRequestWrapper, resp);
    }

    public void destroy() {

    }

}
