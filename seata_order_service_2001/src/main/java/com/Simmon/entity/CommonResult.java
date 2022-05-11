package com.Simmon.entity;

import lombok.Data;

@Data
public class CommonResult<T> {
    private Integer code;
    private String  message;
    private T data;

}
