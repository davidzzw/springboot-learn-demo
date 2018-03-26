package com.zzw.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzw
 * @see
 * @since 2018/2/23
 */
@Configuration
public class KakfaConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //批量接收消息
    @Bean
    public KafkaListenerContainerFactory kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);
        factory.setBatchListener(true);//设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);//设置提交偏移量的方式
        return factory;
    }

    @Bean
    public DefaultKafkaConsumerFactory<String,String> consumerFactory() {
        DefaultKafkaConsumerFactory<String,String> consumerFactory = new DefaultKafkaConsumerFactory<String, String>(consumerConfigs());
        return consumerFactory;
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.20.7.49:9092");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);//定时提交功能，开启后，Kafka会定期向zk中更新我们consumer获取的最后一个batch的first mesasage offset
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");//向zk更新offset的时间间隔
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group1");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");//表示一次获取的最多记录数
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");//如果zookeeper上没有offset合理的初始值情况下获取第一条消息开始的策略smallest|largeset
        return props;
    }

    //批量消费
   /* @KafkaListener(topics = "${tgs.kafka.topics}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        logger.info("records.size: " + records.size() + " in all");
        for (ConsumerRecord<?, ?> record : records) {

        }
        try {

        } catch (Exception e) {

        }finally{
            logger.info("start commit offset");
            ack.acknowledge();//手动提交偏移量
            logger.info("stop commit offset");
        }
    }*/
}
