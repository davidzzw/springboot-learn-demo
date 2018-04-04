package com.zzw;

import com.zzw.vo.UserVo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootTestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootTestApplication.class, args);
		/*UserVo bean = context.getBean(UserVo.class);
		System.out.println(bean);*/
	}
}
