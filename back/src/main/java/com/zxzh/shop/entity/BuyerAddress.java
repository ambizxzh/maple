package com.zxzh.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicInsert//动态插入,使得没有赋值给数据库的值,数据库自动生成(针对数据库设置了自动生成的值)
@DynamicUpdate//动态修改
@AllArgsConstructor
@NoArgsConstructor
public class BuyerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String areaCode;
    private Date updateTime;//时间,在设计数据库时,让数据库自动生成
    private Date createTime;


}
