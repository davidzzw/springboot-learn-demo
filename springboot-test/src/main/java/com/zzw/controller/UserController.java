package com.zzw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @see
 * @since 2018/1/25
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/hello")
    public String hello(){
        logger.info("hello");
        return "hello";
    }
}
