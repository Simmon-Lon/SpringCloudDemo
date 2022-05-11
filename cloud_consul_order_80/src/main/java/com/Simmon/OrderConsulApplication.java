package com.Simmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulApplication.class,args);
    }
}
