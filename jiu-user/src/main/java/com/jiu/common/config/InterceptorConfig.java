package com.jiu.common.config;

import com.jiu.common.Interceptor.WebInterceptor;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author lujj
 * @Date 2018/10/26 22:42
 */
@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Bean
    public WebInterceptor webInterceptor(){
        return new WebInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor());
    }
}
