package com.zxzh.shop.enums;

import lombok.Getter;

@Getter
public enum  PayStatusEnum {

    UNPAID(0,"未支付"),//注意这是逗号
    PAID(1,"已支付");
    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
