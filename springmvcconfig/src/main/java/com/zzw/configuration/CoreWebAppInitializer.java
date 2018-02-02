package com.zzw.configuration;


import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

/**
 * {@link AbstractAnnotationConfigDispatcherServletInitializer}
 * @author zzw
 * @see
 * @since 2018/2/2
 */
public class CoreWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //引入Spring的配置类
        return new Class<?>[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //引入Springmvc的配置类
        return new Class<?>[] {SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // 默认DispatcherServlet的映射,拦截所有请求
        return new String[]{"/*"};
    }

    @Override
    protected Filter[] getServletFilters() {
        //定义字符过滤器
        return new Filter[]{new CharacterEncodingFilter("utf-8", true)};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 扩展修改默认实现的DispatcherServlet
        super.customizeRegistration(registration);
    }

}
