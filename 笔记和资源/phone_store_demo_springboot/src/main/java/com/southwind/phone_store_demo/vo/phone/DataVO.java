package com.southwind.phone_store_demo.vo.phone;

import com.southwind.phone_store_demo.vo.phone.PhoneCategoryVO;
import com.southwind.phone_store_demo.vo.phone.PhoneInfoVO;
import lombok.Data;

import java.util.List;

@Data
public class DataVO {
    private List<PhoneCategoryVO> categories;
    private List<PhoneInfoVO> phones;
}
