package com.zxzh.shop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@Entity
public class PhoneSpecs {//商品规格(说明书)实体,对应数据库中的商品规格表格
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specsId;
    private Integer phoneId;//数据库里用的是varchar即String
    private String specsName;
    private Integer specsStock;
    private BigDecimal specsPrice;
    private String specsIcon;
    private String specsPreview;
    private Date updateTime;
    private Date createTime;
}
