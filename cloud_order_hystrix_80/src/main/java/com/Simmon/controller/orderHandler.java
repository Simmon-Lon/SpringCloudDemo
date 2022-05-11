package com.Simmon.controller;

import com.Simmon.service.PaymentFeign;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
@DefaultProperties(defaultFallback = "PaymentFallBack")
public class orderHandler {

    @Autowired
    private PaymentFeign paymentFeign;

    @GetMapping("/hystrix/paymentInfo/{id}")
    /*@HystrixCommand*/
    public String PaymentInfo(@PathVariable("id") Integer id){
        String result=paymentFeign.PaymentInfo(id);
        log.info("*****result:"+result);
        return result;
    }

    @GetMapping("/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "TimeOutHandler",commandProperties = {@HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds",value = "5000") })
    /*@HystrixCommand*/
    public String TimeOut(@PathVariable("id") Integer id){
        String result=paymentFeign.TimeOut(id);
        log.info("*****result:"+result);
        return result;
    }

    public String TimeOutHandler(@PathVariable("id") Integer id){
        return "哦豁报错咯"+id;
    }

    /*这是默认的服务降级*/
    public String PaymentFallBack(){
        return "Global异常处理信息,请稍后重试";
    }
}
