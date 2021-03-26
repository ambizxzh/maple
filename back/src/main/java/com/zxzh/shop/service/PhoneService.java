package com.zxzh.shop.service;

import com.zxzh.shop.entity.PhoneSpecs;
import com.zxzh.shop.vo.phone.TopDataVO;
import com.zxzh.shop.vo.phone.TopPhoneInfoVO;
import com.zxzh.shop.vo.phone.TopSpecsVO;

import java.util.List;

public interface PhoneService {//接口为了找到封装好的信息，而非数据库原始信息
    public TopDataVO findDataVO();//查找视图层数据封装
    public List<TopPhoneInfoVO>  findPhoneInfoVOByCategoryType(Integer categoryType);//给视图层(VO)找到手机信息,也就是找封装好的信息
    public TopSpecsVO findTopSpecsVOByPhoneId(Integer phoneId);
    public void updateStock(Integer specsId, Integer quantity);
}
