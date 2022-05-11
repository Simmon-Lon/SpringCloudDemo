package com.Simmon.controller;


import com.Simmon.entity.CommonResult;
import com.Simmon.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderHandler {

    /*public static final String PAYMENT_URL="http://localhost:8001/payment";*/
    public static final String PAYMENT_URL="http://consul-provider-payment/payment";


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String paymentInfo(){
        String result=restTemplate.getForObject(PAYMENT_URL+"/consul",String.class);
        return result;
    }

}
