package com.zxzh.shop.vo.phone;

import lombok.Data;

import java.util.List;

@Data
public class TopDataVO {
    private List<PhoneCategoryVO> categories;
    private List<TopPhoneInfoVO> phones;
}
