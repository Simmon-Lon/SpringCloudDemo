package com.simmon.myhandler;

import com.Simmon.entity.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException blockException){
        return new CommonResult(400,"按客户自定义名称限流测试error handlerException----1");
    }
    public static CommonResult handlerException2(BlockException blockException){
        return new CommonResult(400,"按客户自定义名称限流测试error handlerException----2");
    }
}
