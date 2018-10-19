package com.zzw.web;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public CommonFilter commonFilter(){
        CommonFilter commonFilter = new CommonFilter();
        WebCallbackManager.setRequestOriginParser(new MyRequestOriginParser());
        return new CommonFilter();
    }

   /* @Bean
    public CsfNacosDataSourceConfiguration csfNacosDataSourceConfiguration(){
        return new CsfNacosDataSourceConfiguration();
    }*/
   /* @Bean
    public ClothoFilter clothoFilter(){
        return new ClothoFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean1(ClothoFilter clothoFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(clothoFilter);
        registration.addInitParameter("useLocalIp","true");
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("clotho");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }
*/
    @Bean
    public FilterRegistrationBean filterRegistrationBean2(CommonFilter commonFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(commonFilter);//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.addInitParameter("name", "alue");//添加默认参数
        registration.setName("MyFilter");//设置优先级
        registration.setOrder(2);//设置优先级
        return registration;
    }

    /*@Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);
        config.setMaxWaitMillis(1000);
        config.setMaxTotal(100);
        return config;
    }*/

    /*@Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setTimeout(1000);
        return jedisConnectionFactory;
    }

    @Bean
    public CRedisTemplate<String,String> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
        CRedisTemplate<String, String> template = new CRedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }*/
}
