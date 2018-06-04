package com.zzw;

import com.zzw.task.MyTask;
import javafx.concurrent.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot2TestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private MyTask task;

	@Test
	public void test() throws Exception {

		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();

		Thread.currentThread().join();
		System.out.println("***********");
	}

}
