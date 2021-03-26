package com.zxzh.shop.vo;

import lombok.Data;

@Data
public class PublicResponseBodyVO<T> {//<T>设置泛型
    private Integer code;
    private String msg;
    private T data;
}
