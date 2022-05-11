package com.Simmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class nacosApplication_2 {
    public static void main(String[] args) {
        SpringApplication.run(nacosApplication_2.class,args);
    }
}
