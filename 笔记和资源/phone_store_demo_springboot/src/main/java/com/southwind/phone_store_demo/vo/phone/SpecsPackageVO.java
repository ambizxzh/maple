package com.southwind.phone_store_demo.vo.phone;

import com.southwind.phone_store_demo.vo.phone.SkuVO;
import lombok.Data;

import java.util.Map;

@Data
public class SpecsPackageVO {
    private Map<String,String> goods;
    private SkuVO sku;
}
