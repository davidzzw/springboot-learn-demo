package com.zzw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author zzw
 * @see
 * @since 2018/2/2
 */
@Configuration
@ComponentScan(basePackages="com.zzw.controller")
@EnableWebMvc
public class SpringMvcConfig {

    //配置视图解析器
    /*@Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }*/
}
