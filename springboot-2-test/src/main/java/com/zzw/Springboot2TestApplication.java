package com.zzw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@SpringBootApplication
public class Springboot2TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2TestApplication.class, args);
	}

	@Bean
	public DataLoader dataLoader() {
		return new DataLoader();
	}

	@Slf4j
	static class DataLoader implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			log.info("Loading data...");
		}
	}

	@EnableAsync
	@Configuration
	class TaskPoolConfig {

		@Bean("taskExecutor")
		public Executor taskExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(10);
			executor.setMaxPoolSize(20);
			executor.setQueueCapacity(200);
			executor.setKeepAliveSeconds(60);
			executor.setThreadNamePrefix("taskExecutor-");
			executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
			executor.setWaitForTasksToCompleteOnShutdown(true);
			return executor;
		}
	}
}
