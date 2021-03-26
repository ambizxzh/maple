package com.zxzh.shop.vo.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressVO {
    @JsonProperty("areaCode")
    private String areaCode;
    @JsonProperty("id")
    private Integer addressId;
    @JsonProperty("name")
    private String buyerName;
    @JsonProperty("tel")
    private String buyerPhone;
    @JsonProperty("address")
    private String buyerAddress;
}
