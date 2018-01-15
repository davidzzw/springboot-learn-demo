package com.zzw.controller;

import com.zzw.service.UserService;
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
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        userService.test();
        return "success";
    }
}
