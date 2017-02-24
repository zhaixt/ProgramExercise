package com.spring.exercize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beanRefContext.xml")
//@EnableDiscoveryClient
@EnableAutoConfiguration
@Configuration
public class SpringRelatedExcersizeApplication {

	public static void main(String[] args) {
		System.setProperty("DEPLOY_ENV", "dev");// TODO  上线前请去掉此句，在本机设置好环境变量也可去掉此句
		SpringApplication.run(SpringRelatedExcersizeApplication.class, args);
	}

}
