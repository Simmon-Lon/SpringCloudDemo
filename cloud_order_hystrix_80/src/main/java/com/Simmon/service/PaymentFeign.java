package com.Simmon.service;

import com.Simmon.service.impl.PaymentFeignService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-hystrix-payment",fallback = PaymentFeignService.class)
public interface PaymentFeign {
    @GetMapping("/payment/hystrix/paymentInfo/{id}")
    public String PaymentInfo(@PathVariable("id") Integer id);
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String TimeOut(@PathVariable("id") Integer id);
}
