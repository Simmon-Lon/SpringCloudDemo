package com.Simmon.controller;

import com.Simmon.entity.CommonResult;
import com.Simmon.entity.Payment;
import com.Simmon.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentHandler {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("***插入结果"+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功,serverPort"+serverPort,result);
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

    @GetMapping("/lb")
    public String getPayment(){
        return serverPort;
    }
}
