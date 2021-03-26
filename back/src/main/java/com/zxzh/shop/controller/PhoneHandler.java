package com.zxzh.shop.controller;


import com.zxzh.shop.service.PhoneService;
import com.zxzh.shop.util.ResponseBodyResultUtil;
import com.zxzh.shop.vo.PublicResponseBodyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phone")
public class PhoneHandler {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/index")
    public PublicResponseBodyVO index(){
        return ResponseBodyResultUtil.success(phoneService.findDataVO());
    }

    @GetMapping("/findByCategoryType/{categoryType}")
    public PublicResponseBodyVO findByCategoryType(
            @PathVariable("categoryType") Integer categoryType){
        return ResponseBodyResultUtil.success(phoneService.findPhoneInfoVOByCategoryType(categoryType));
    }

    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public PublicResponseBodyVO findSpecsByPhoneId(
            @PathVariable("phoneId") Integer phoneId){
        return ResponseBodyResultUtil.success(phoneService.findTopSpecsVOByPhoneId(phoneId));
    }
}
