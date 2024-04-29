package com.infob.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(EApiGatewayApplication.class, args);
    }
}
