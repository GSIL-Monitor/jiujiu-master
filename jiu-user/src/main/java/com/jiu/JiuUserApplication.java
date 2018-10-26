package com.jiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JiuUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiuUserApplication.class, args);
	}
}
