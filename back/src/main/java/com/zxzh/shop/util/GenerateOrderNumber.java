package com.zxzh.shop.util;

import java.util.Random;

public class GenerateOrderNumber {
    public static synchronized String createOrderNumber(){//synchronized线程安全,使得同时创建多个订单时不产生重复的订单号
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;//6位随机数
        return System.currentTimeMillis()+String.valueOf(number);//毫秒和随机来构成非重复的订单号
    }
}
