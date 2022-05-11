package com.Simmon.controller;

import com.Simmon.entity.CommonResult;
import com.Simmon.entity.Payment;
import com.Simmon.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentHandler {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("***插入结果"+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("***插入结果"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询数据库成功,serverPort"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询数据库失败该id失败--->"+id,null);
        }
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> list=discoveryClient.getServices();
        for (String element : list) {
            log.info("*****element*****");
        }

        List<ServiceInstance> instances=discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/lb")
    public String getPayment(){
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    public String PaymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin(){
        return "hello welcome zipkin RestTemple.";
    }
}
