package com.Simmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class nacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(nacosApplication.class,args);
    }
}
