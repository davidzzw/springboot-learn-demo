package com.zzw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzw.sender.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzw
 * @see
 * @since 2018/2/23
 */
@RestController
public class SendController {

    @Autowired
    private KafkaSender sender;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/send")
    public String sendMessage(){
        try {
            sender.send();
            return "error";
        } catch (JsonProcessingException e) {
            logger.info("sendMessage error");
        }
        return "sucess";
    }
}
