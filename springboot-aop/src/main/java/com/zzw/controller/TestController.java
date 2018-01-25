package com.zzw.controller;

import com.zzw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @see
 * @since 2018/1/15
 */
@RestController
public class TestController implements InitializingBean{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        userService.test();
        return "success";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("***********");
    }
}
