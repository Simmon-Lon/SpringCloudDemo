package com.Simmon.service.impl;

import com.Simmon.entity.CommonResult;
import com.Simmon.entity.Payment;
import com.Simmon.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProdcutServiceimpl implements ProductService {
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(444,"服务降级返回",new Payment(id,"error"));
    }
}
