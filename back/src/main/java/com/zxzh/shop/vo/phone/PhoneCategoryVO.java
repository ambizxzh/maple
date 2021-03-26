package com.zxzh.shop.vo.phone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //带参构造(带参数的构造函数)
public class PhoneCategoryVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;

}
