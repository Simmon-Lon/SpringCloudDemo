package com.Simmon.service.impl;

import com.Simmon.service.PaymentFeign;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignService implements PaymentFeign {
    public String PaymentInfo(Integer id) {
        return "paymentFeign fall back paymentInfo";
    }

    public String TimeOut(Integer id) {
        return "paymentFeign fall back paymentTimeout";
    }
}
