package com.zzw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootLog4j2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLog4j2DemoApplication.class, args);
	}
}
