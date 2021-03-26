package com.zxzh.shop.repository;

import com.zxzh.shop.entity.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerAddressRepository extends JpaRepository<BuyerAddress, Integer> {
    public BuyerAddress findByAddressId(Integer addressId);
}
