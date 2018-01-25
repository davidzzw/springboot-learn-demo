package com.zzw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @see
 * @since 2018/1/25
 */
@Controller
public class ForwordController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/forward",method = RequestMethod.POST)
    public String forward(){
        logger.info("forward");
        return "forward:/hello";
    }
}
