package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RestTemplate restTemplate;

	@org.junit.Test
	public void gcTestFirst() {

		for (int i = 0; i < 1000; i++) {
			restTemplate.getForEntity("http://localhost:8081/gcTest", String.class);
		}
	}

}
