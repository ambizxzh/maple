package com.zxzh.shop.repository;

import com.zxzh.shop.entity.PhoneSpecs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneSpecsRepositoryTest {
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Test
    void findAll(){
        List<PhoneSpecs> list=phoneSpecsRepository.findAll();
        for(PhoneSpecs phoneSpecs:list){
            System.out.println(phoneSpecs);
        }
    }

    @Test
    void findByPhoneId(){
        List<PhoneSpecs> list=phoneSpecsRepository.findAllByPhoneId(1);
        for(PhoneSpecs phoneSpecs:list){
            System.out.println(phoneSpecs);
        }
    }

}
