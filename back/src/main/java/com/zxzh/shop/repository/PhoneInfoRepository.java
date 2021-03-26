package com.zxzh.shop.repository;

import com.zxzh.shop.entity.PhoneInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PhoneInfoRepository extends JpaRepository<PhoneInfo, Integer> {
    public List<PhoneInfo> findAllByCategoryType(Integer categoryType);
}
