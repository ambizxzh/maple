package com.zxzh.shop.dto;

import lombok.Data;

@Data
public class OrderDTO {//Data To Object(DTO)业务数据转换成数据库数据对象
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer specsId;
    private Integer phoneQuantity;
}
