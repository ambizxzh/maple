package com.southwind.phone_store_demo.vo.phone;

import com.southwind.phone_store_demo.vo.phone.PhoneSpecsVO;
import lombok.Data;

import java.util.List;

@Data
public class TreeVO {
    private String k = "规格";
    private List<PhoneSpecsVO> v;
    private String k_s = "s1";
}
