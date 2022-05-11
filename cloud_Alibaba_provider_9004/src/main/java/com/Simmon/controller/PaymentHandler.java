package com.Simmon.controller;

import com.Simmon.entity.CommonResult;
import com.Simmon.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentHandler {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap =new HashMap<Long, Payment>();
    static {
        hashMap.put(1L,new Payment(1L,"asdasdadadadadadadad"));
        hashMap.put(2L,new Payment(2L,"asdasdasdasdwrasfafa"));
        hashMap.put(3L,new Payment(3L,"vsdvsddsffdsfdsfdsfs"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment =hashMap.get(id);
        CommonResult<Payment> result=new CommonResult<Payment>(200,"from sql,serverPort:"+serverPort,payment);
        return result;
    }
}
