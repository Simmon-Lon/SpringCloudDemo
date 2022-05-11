package com.Simmon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class nacosHandler {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/echo/{id}")
    public String echo(@PathVariable("id") String id){
        return "Hello Nacos Discovery "+id+"\t"+"端口号:"+serverPort;
    }
}
