/*
 * FileName: HomeController.java
 * Author:   zzw
 * Date:     2018年05月15日
 * Description:
 */
package com.zzw.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
@RestController
public class HomeController {

    @RequestMapping("start")
    public String start() throws InterruptedException, IOException {
        TimeUnit.MILLISECONDS.sleep(2000);
        return "hello";
    }
}
