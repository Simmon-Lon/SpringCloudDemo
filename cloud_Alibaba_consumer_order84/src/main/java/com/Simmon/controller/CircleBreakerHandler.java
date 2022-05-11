package com.Simmon.controller;

import com.Simmon.entity.CommonResult;
import com.Simmon.entity.Payment;
import com.Simmon.service.ProductService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/consumer")
@Slf4j
public class CircleBreakerHandler {
    public static final String SERVICE_URL="http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/fallback/{id}")
    //@SentinelResource(value = "fallback")没有fallback
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "handlerFallback",
    exceptionsToIgnore = {IllegalArgumentException.class})/*exceptionsToIgnore:不考虑的异常*/
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result=restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);
        if (id==4){
            throw new IllegalArgumentException("IllegalArgument,非法参数异常");
        }else if(result.getData()==null){
            throw new NullPointerException("NullPointer,没有该id记录,空指针异常");
        }
        return result;
    }
    /*fallback*/
    public CommonResult<Payment> handlerFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment=new Payment(id,null);
        return new CommonResult<Payment>(444,"兜底异常handlerFallback,exception内容"+e.getMessage(),payment);
    }
    /*blockHandler*/
    public CommonResult<Payment> blockHandler(@PathVariable("id") Long id, BlockException e){
        Payment payment=new Payment(id,null);
        return new CommonResult<Payment>(445,"被sentinel限流,blockException内容"+e.getMessage(),payment);
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return productService.paymentSQL(id);
    }
}
