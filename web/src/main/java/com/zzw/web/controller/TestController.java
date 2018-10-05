/*
 * FileName: TestController.java
 * Author:   zzw
 * Date:     2018年09月22日
 * Description:
 */
package com.zzw.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
@RestController
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    @SentinelResource("resource")
    public String hello(){
        redisTemplate.opsForValue().set("1","1");
        String s = (String) redisTemplate.opsForValue().get("1");
        return s;
    }
}
