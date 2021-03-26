package com.zxzh.shop.exception;


import com.zxzh.shop.enums.ExceptionEnum;

public class StockLackException extends RuntimeException{//自定义异常

    public StockLackException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
    }
    public StockLackException(String error) {
        super(error);
    }

}
