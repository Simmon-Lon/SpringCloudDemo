package com.Simmon.service;

import com.Simmon.entity.CommonResult;
import com.Simmon.entity.Payment;
import com.Simmon.service.impl.ProdcutServiceimpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = ProdcutServiceimpl.class)
@Repository
public interface ProductService {
    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
