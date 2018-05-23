package com.zzw.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzw.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author zzw
 * @see
 * @since 2018/1/29
 */
@Component
public class KafkaSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    //发送消息方法
    public void send() throws JsonProcessingException, ExecutionException, InterruptedException {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        String msg = objectMapper.writeValueAsString(message);
        logger.info("+++++++++++++++++++++  message = {}", msg);
        ListenableFuture<SendResult<String, String>> reslt = kafkaTemplate.send("test2",1, msg);
        System.out.println(reslt.get().toString());
    }
}
