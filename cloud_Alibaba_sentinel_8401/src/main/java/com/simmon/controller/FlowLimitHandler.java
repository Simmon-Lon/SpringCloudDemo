package com.simmon.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitHandler {

    @GetMapping("/testa")
    public String testA(){
        return "----testA";
    }

    @GetMapping("/testb")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"....testB");
        return "----testB";
    }
    @GetMapping("/testd")
    public String testD(){
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD,测试RT");
        */

        log.info("testD,异常比例");
        int num=10/0;
        return "----testD";
    }

    @GetMapping("/teste")
    public String testE(){
        log.info("testE,测试异常数");
        int num = 10/0;
        return "----testE";
    }

    @GetMapping("/testHotKey")
    /*value可以自定义blockHandler出现降级进入降级方法*/
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "-----testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "----deal_testHotKey";
    }
}
