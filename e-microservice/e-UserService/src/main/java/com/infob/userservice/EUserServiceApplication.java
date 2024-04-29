package com.infob.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EUserServiceApplication.class, args);
	}

}
