package com.zxzh.shop.controller;


import com.zxzh.shop.exception.StockLackException;
import com.zxzh.shop.requestform.AddressForm;
import com.zxzh.shop.service.AddressService;
import com.zxzh.shop.util.ResponseBodyResultUtil;
import com.zxzh.shop.vo.PublicResponseBodyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressHandler {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public PublicResponseBodyVO list(){
        return ResponseBodyResultUtil.success(addressService.findAll());
    }

    @PostMapping("/create")
    public PublicResponseBodyVO create(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【添加地址】参数错误,addressForm={}",addressForm);
            throw new StockLackException(bindingResult.getFieldError().getDefaultMessage());
        }

        addressService.createOrUpdate(addressForm);

        return ResponseBodyResultUtil.success(null);
    }

    @PutMapping("/update")
    public PublicResponseBodyVO update(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【修改地址】参数错误,addressForm={}",addressForm);
            throw new StockLackException(bindingResult.getFieldError().getDefaultMessage());
        }

        addressService.createOrUpdate(addressForm);

        return ResponseBodyResultUtil.success(null);
    }
}
