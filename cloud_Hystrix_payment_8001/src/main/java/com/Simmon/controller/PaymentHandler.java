package com.Simmon.controller;

import com.Simmon.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentHandler {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hystrix/paymentInfo/{id}")
    public String PaymentInfo(@PathVariable("id") Integer id){
        String result=paymentService.paymentInfo(id);
        log.info("*****result:"+result);
        return result;
    }

    @GetMapping("/hystrix/timeout/{id}")
    public String TimeOut(@PathVariable("id") Integer id){
        String result=paymentService.Timeout(id);
        log.info("*****result:"+result);
        return result;
    }

    /*服务熔断*/
    @GetMapping("/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result=paymentService.paymentCircuitBreaker(id);
        log.info("*****result"+result);
        return result;
    }
}
