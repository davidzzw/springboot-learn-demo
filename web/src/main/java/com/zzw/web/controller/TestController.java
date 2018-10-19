/*
 * FileName: TestController.java
 * Author:   zzw
 * Date:     2018年09月22日
 * Description:
 */
package com.zzw.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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

    @GetMapping("/hello")
    @SentinelResource("hello")
    public String hello(){
        return "hello";
    }
}
