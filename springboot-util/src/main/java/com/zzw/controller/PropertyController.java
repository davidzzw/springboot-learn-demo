package com.zzw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @see
 * @description 解决配置文件乱码
 * @since 2018/1/26
 */
@RestController
//@PropertySource(value = {"classpath:application.properties"},encoding="utf-8")
public class PropertyController {

    @Value("${hello}")
    private String hello;

    @GetMapping("/hello")
    public String hello(){
        return hello;
    }
}
