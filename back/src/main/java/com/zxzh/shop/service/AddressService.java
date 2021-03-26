package com.zxzh.shop.service;

import com.zxzh.shop.requestform.AddressForm;
import com.zxzh.shop.vo.address.AddressVO;

import java.util.List;

public interface AddressService{
    public List<AddressVO> findAll();//查找用户的地址列表
    public void createOrUpdate(AddressForm addressForm);
}
