package com.sgcai.boot.api.bootapi.config;

import com.sgcai.boot.api.bootapi.filter.WebRequestWrapperFilter;
import com.sgcai.boot.api.bootapi.interceptor.AuthInterceptor;
import com.sgcai.boot.api.bootapi.interceptor.FileUploadInterceptor;
import com.sgcai.boot.api.bootapi.interceptor.OnlineUserInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public FileUploadInterceptor fileUploadInterceptor() {
        FileUploadInterceptor fileUploadInterceptor = new FileUploadInterceptor();
        fileUploadInterceptor.setMaxSize(1024 * 1024 * 10);
        return fileUploadInterceptor;
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        AuthInterceptor authInterceptor = new AuthInterceptor();
        return authInterceptor;
    }

    @Bean
    public OnlineUserInterceptor onlineUserInterceptor() {
        OnlineUserInterceptor onlineUserInterceptor = new OnlineUserInterceptor();
        return onlineUserInterceptor;
    }


    /**
     * 配置自定义过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new WebRequestWrapperFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }

    /**
     * 配置自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(onlineUserInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
        registry.addInterceptor(fileUploadInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
    }
}
