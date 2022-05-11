package com.Simmon.service.impl;

import cn.hutool.core.util.IdUtil;
import com.Simmon.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService
{
    public String paymentInfo(Integer id) {
        return "线程池"+Thread.currentThread().getName()+"paymentInfo,id"+id+"\t"+"成功";
    }

    @HystrixCommand(fallbackMethod = "TimeoutHandler",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String Timeout(Integer id) {
        int timeNum=3;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"Timeout,id"+id+"\t"+"哦豁"+"耗时"+timeNum+"秒";
    }

    public String TimeoutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"TimeOutHandler,id:"+id+"哦豁找不到咯";
    }

    /*服务熔断*/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后熔断
    })
    public String paymentCircuitBreaker(Integer id){
        if (id<0){
            throw new RuntimeException("*****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();/*UUID.randomUUID().toString();*/
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号"+serialNumber;
    }

    public String paymentCircuitBreakerFallBack(Integer id){
        return "id 不能为负数,请稍后重试!!!-----id:"+id;
    }
}
