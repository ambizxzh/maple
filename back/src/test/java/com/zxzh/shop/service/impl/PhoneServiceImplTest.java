package com.zxzh.shop.service.impl;

import com.zxzh.shop.vo.phone.TopDataVO;
import com.zxzh.shop.vo.phone.TopPhoneInfoVO;
import com.zxzh.shop.vo.phone.TopSpecsVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PhoneServiceImplTest {
    @Autowired
    private PhoneServiceImpl phoneServiceImpl;

    @Test
    void findDataVO(){
        TopDataVO topDataVO =phoneServiceImpl.findDataVO();
        System.out.println();
    }
    @Test
    void findPhoneInfoByCategoryType(){
        List<TopPhoneInfoVO> list=phoneServiceImpl.findPhoneInfoVOByCategoryType(2);
        System.out.println();
    }
    @Test
    void findTopSpecsVO(){
        TopSpecsVO topSpecsVO=phoneServiceImpl.findTopSpecsVOByPhoneId(1);
        System.out.println();
    }
    @Test
    void updateStock(){
        phoneServiceImpl.updateStock(1,2);
    }

}
