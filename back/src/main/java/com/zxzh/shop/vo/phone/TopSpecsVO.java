package com.zxzh.shop.vo.phone;

import lombok.Data;

import java.util.Map;
@Data
public class TopSpecsVO {//商品规格
    private Map<String, String> goods;
    private SkuVO sku;
}
