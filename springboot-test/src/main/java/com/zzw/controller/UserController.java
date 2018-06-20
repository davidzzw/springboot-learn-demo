package com.zzw.controller;

import com.zzw.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzw
 * @see
 * @since 2018/1/25
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${hello}")
    private String hello;

    @GetMapping("/hello")
    public String hello(){
        return hello;
    }

    @GetMapping("/users")
    public List<UserVo>  users(){
        logger.info(hello);
        List<UserVo> list = new ArrayList<>();
        list.add(new UserVo("1","1"));
        list.add(new UserVo("2","2"));
        list.add(new UserVo("3","3"));
        return list;
    }
}
