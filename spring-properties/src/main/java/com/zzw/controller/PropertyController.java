package com.zzw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @see
 * @since 2018/2/22
 */
@RestController
public class PropertyController {

    @Autowired
    private Environment environment;

    @GetMapping("/test")
    public String test(){
        System.out.println(environment.getProperty("url"));
        return environment.getProperty("test");
    }
}
