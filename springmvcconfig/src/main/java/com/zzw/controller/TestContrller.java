package com.zzw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @see
 * @since 2018/2/2
 */
@RestController
public class TestContrller {

    @GetMapping
    public String hello(){
        return "hello";
    }
}
