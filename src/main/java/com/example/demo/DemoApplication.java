package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class DemoApplication {

	public static void main(String[] args) {
  		SpringApplication.run(DemoApplication.class, args);
	}


	/**
	 * springboot启动类中创建filter实例
	 */
//	@Bean
//	public FilterRegistrationBean sessionFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		SessionFilter sessionFilter = new SessionFilter();
//		registrationBean.setFilter(sessionFilter);
//		registrationBean.setOrder(Integer.MIN_VALUE);
//		registrationBean.addUrlPatterns("/*");
//		return registrationBean;
//	}

}
