package com.example.springboot.config;

import com.example.springboot.filter.TestFilter1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration
public class FilterConfig {

    public FilterRegistrationBean testFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new TestFilter1());
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("testFilter1");
        //过滤器顺序
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }

}
