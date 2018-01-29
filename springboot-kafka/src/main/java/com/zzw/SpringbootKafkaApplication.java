package com.zzw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzw.sender.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootKafkaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootKafkaApplication.class, args);
		KafkaSender sender = context.getBean(KafkaSender.class);

		/*for (int i = 0; i < 3; i++) {
			//调用消息发送类中的消息发送方法
			try {
				sender.send();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
	}
}
