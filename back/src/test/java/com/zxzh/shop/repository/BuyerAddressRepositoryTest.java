package com.zxzh.shop.repository;

import com.zxzh.shop.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressRepositoryTest {

    @Autowired
    private BuyerAddressRepository buyerAddressRepository;
    @Test
    void findAll(){
        List<BuyerAddress> list=buyerAddressRepository.findAll();
        for(BuyerAddress buyerAddress:list){
            System.out.println(buyerAddress);
        }
    }
    @Test
    void findByAddressId(){
        System.out.println(buyerAddressRepository.findByAddressId(1));
    }

    @Test
    void save(){//保存
        BuyerAddress buyerAddress=new BuyerAddress();
        buyerAddress.setAreaCode("110101");
        buyerAddress.setBuyerAddress("北京市海淀区西土城路 12 智慧大厦 7 楼 501 室");
        buyerAddress.setBuyerName("贝优");
        buyerAddress.setBuyerPhone("13366668888");
        buyerAddressRepository.save(buyerAddress);
    }

    @Test
    void update(){
        BuyerAddress buyerAddress=buyerAddressRepository.findById(35).get();//返回optional类型,防止出现控制异常,
        // 使用get()进行获取值,是Java8新特性
        buyerAddress.setBuyerName("枫树");
        buyerAddressRepository.save(buyerAddress);
    }
}
