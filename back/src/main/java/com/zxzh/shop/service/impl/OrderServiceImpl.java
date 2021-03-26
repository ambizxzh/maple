package com.zxzh.shop.service.impl;

import com.zxzh.shop.dto.OrderDTO;
import com.zxzh.shop.entity.OrderMaster;
import com.zxzh.shop.entity.PhoneInfo;
import com.zxzh.shop.entity.PhoneSpecs;
import com.zxzh.shop.enums.ExceptionEnum;
import com.zxzh.shop.enums.PayStatusEnum;
import com.zxzh.shop.exception.StockLackException;
import com.zxzh.shop.repository.OrderMasterRepository;
import com.zxzh.shop.repository.PhoneInfoRepository;
import com.zxzh.shop.repository.PhoneSpecsRepository;
import com.zxzh.shop.service.OrderService;
import com.zxzh.shop.service.PhoneService;
import com.zxzh.shop.util.GenerateOrderNumber;
import com.zxzh.shop.vo.order.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PhoneService phoneService;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {//从数据库中找到数据,填充给OrderMaster
        OrderMaster orderMaster=new OrderMaster();//数据目的地
        BeanUtils.copyProperties(orderDTO,orderMaster);

        PhoneSpecs phoneSpecs=phoneSpecsRepository.findById(orderDTO.getSpecsId()).get();//根据提供的specsId在规格表查找信息
        PhoneInfo phoneInfo=phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();//根据刚刚得到的规格信息中的PhoneId查找信息
        //将信息复制给orderMaster
        BeanUtils.copyProperties(phoneSpecs,orderMaster);
        BeanUtils.copyProperties(phoneInfo,orderMaster);

        //计算给orderMaster的总价
        BigDecimal orderAmount=new BigDecimal(0);
        orderAmount = phoneSpecs.getSpecsPrice().divide(new BigDecimal(100))//将分化成元
                .multiply(new BigDecimal(orderDTO.getPhoneQuantity()))//乘以数量
                .add(orderAmount);//加上之前的总价
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderId(GenerateOrderNumber.createOrderNumber());
        orderDTO.setOrderId(orderMaster.getOrderId());
        //payStatus
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());//创建订单时为未支付

        orderMasterRepository.save(orderMaster);
        phoneService.updateStock(orderDTO.getSpecsId(),orderDTO.getPhoneQuantity());
        return orderDTO;
    }

    @Override
    public OrderDetailVO findOrderDetailByOrderId(String orderId) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();

        if(orderMaster == null){
            log.error("【查询订单】订单不存在,orderMaster={}",orderMaster);
            throw new StockLackException(ExceptionEnum.ORDER_NOT_EXIST);
        }

        BeanUtils.copyProperties(orderMaster,orderDetailVO);
        orderDetailVO.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100))+".00");

        return orderDetailVO;
    }

    @Override
    public String pay(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();

        if(orderMaster == null){
            log.error("【支付订单】订单不存在,orderMaster={}",orderMaster);
            throw new StockLackException(ExceptionEnum.PHONE_STOCK_ERROR);
        }

        if(orderMaster.getPayStatus().equals(PayStatusEnum.UNPAID.getCode())){
            orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
            orderMasterRepository.save(orderMaster);
        } else {
            log.error("【支付订单】订单已支付,orderMaster={}",orderMaster);
        }

        return orderId;
    }
}
