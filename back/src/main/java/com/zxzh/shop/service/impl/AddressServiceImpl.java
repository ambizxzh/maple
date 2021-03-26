package com.zxzh.shop.service.impl;

import com.zxzh.shop.entity.BuyerAddress;
import com.zxzh.shop.repository.BuyerAddressRepository;
import com.zxzh.shop.requestform.AddressForm;
import com.zxzh.shop.service.AddressService;
import com.zxzh.shop.vo.address.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private BuyerAddressRepository buyerAddressRepository;
    @Override
    public List<AddressVO> findAll() {
        List<AddressVO> addressVOList= buyerAddressRepository.findAll().stream()
                .map(e-> new AddressVO(
                        e.getAreaCode(),
                        e.getAddressId(),
                        e.getBuyerName(),
                        e.getBuyerPhone(),
                        e.getBuyerAddress()
                )).collect(Collectors.toList());
        return addressVOList;
    }

    @Override
    public void createOrUpdate(AddressForm addressForm) {
        BuyerAddress buyerAddress;//用来接收 请求信息
        if(addressForm.getId()==null){//新增地址
            buyerAddress=new BuyerAddress();

        }else{//修改地址
            buyerAddress=buyerAddressRepository.findById(addressForm.getId()).get();
        }
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(addressForm.getProvince())
                .append(addressForm.getCity())
                .append(addressForm.getCountry())
                .append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(stringBuffer.toString());
        buyerAddress.setAreaCode(addressForm.getAreaCode());
        buyerAddressRepository.save(buyerAddress);
    }

}
