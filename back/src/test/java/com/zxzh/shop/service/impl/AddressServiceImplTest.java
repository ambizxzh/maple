package com.zxzh.shop.service.impl;

import com.zxzh.shop.requestform.AddressForm;
import com.zxzh.shop.vo.address.AddressVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AddressServiceImplTest {
    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @Test
    void findAddressList(){
        List<AddressVO> addressVOList=addressServiceImpl.findAll();
        System.out.println();
    }
    @Test
    void createOrUpdate(){
        AddressForm addressForm=new AddressForm();
        addressForm.setId(36);
        addressForm.setName("隶书");
        addressForm.setTel("13612344321");
        addressForm.setProvince("北京市");
        addressForm.setCity("北京市");
        addressForm.setCountry("东城区");
        addressForm.setAreaCode("110101");
        addressForm.setAddressDetail("168号106室");
        addressServiceImpl.createOrUpdate(addressForm);
    }
}
