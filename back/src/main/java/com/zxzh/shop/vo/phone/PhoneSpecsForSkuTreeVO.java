package com.zxzh.shop.vo.phone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhoneSpecsForSkuTreeVO {//规格例子 也是从数据读出来 v内的内容
    @JsonProperty("id")
    private Integer specsId;
    @JsonProperty("name")
    private String specsName;
    @JsonProperty("imgUrl")
    private String specsIcon;
    @JsonProperty("previewImgUrl")
    private String specsPreview;
}
