package com.zxzh.shop.service.impl;

import com.zxzh.shop.dto.OrderDTO;
import com.zxzh.shop.repository.OrderMasterRepository;
import com.zxzh.shop.util.GenerateOrderNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceImplTest {
@Autowired
    private OrderServiceImpl orderServiceIml;
@Test
    void create(){
    OrderDTO orderDTO=new OrderDTO();
    orderDTO.setBuyerName("张三");
    orderDTO.setBuyerPhone("13678787878");
    orderDTO.setBuyerAddress("广东省深圳市罗湖区科技路123号456室");
    orderDTO.setSpecsId(1);
    orderDTO.setPhoneQuantity(1);
    System.out.println(orderServiceIml.create(orderDTO));
}
}
