package com.zxzh.shop.repository;

import com.zxzh.shop.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    void findAll(){//查找所有订单
        List<OrderMaster> list=orderMasterRepository.findAll();
        for(OrderMaster orderMaster:list){
            System.out.println(orderMaster);
        }
    }



    @Test
    void save(){//新增订单
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setBuyerAddress("浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室");
        orderMaster.setOrderId("132378978942");
        orderMaster.setBuyerPhone("12233335555");
        orderMaster.setBuyerName("樟树");
        orderMaster.setOrderAmount(new BigDecimal(6400));
        orderMaster.setPayStatus(0);
        orderMaster.setPhoneIcon("../static/e22e.jpg");
        orderMaster.setPhoneId(1);
        orderMaster.setPhoneName("Honor 9x");
        orderMaster.setPhoneQuantity(2);
        orderMaster.setSpecsId(1);
        orderMaster.setSpecsName("32GB");
        orderMaster.setSpecsPrice(new BigDecimal(320000));
        orderMasterRepository.save(orderMaster);

    }
    @Test
    void findById(){//根据订单ID查找订单
        OrderMaster orderMaster=orderMasterRepository.findById("132378978942").get();
        System.out.println(orderMaster);
    }

    @Test
    void paid(){//修改支付状态
        OrderMaster orderMaster=orderMasterRepository.findById("132378978942").get();
        orderMaster.setPayStatus(1);
        orderMasterRepository.save(orderMaster);
    }

}
