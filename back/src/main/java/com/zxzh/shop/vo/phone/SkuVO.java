package com.zxzh.shop.vo.phone;


import lombok.Data;

import java.util.List;

@Data
public class SkuVO {//库存
    private List<TreeVO> tree;
    private List<ListVO> list;
    private String price;
    private Integer stock_num;
    private Boolean none_sku=false;
    private Boolean hide_stock=false;
}
