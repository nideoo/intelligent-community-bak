package com.enc.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-21 21:56
 */
@SpringBootConfiguration
public class MySpringMVCConfig implements WebMvcConfigurer {

    @Autowired
    private CommonInterceptor commonInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        InterceptorRegistration registration = registry.addInterceptor(commonInterceptor);     //拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**","/login", "/swagger-ui.html/**");

    }


}