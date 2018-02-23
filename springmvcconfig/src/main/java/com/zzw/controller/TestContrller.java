package com.zzw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @see
 * @since 2018/2/2
 */
@RestController
public class TestContrller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello")
    public String hello(){
        logger.info("hello");
        logger.info("Processing trade with id:[{}] and symbol : [{}] ", 1, 2);
        return "hello";
    }
}
