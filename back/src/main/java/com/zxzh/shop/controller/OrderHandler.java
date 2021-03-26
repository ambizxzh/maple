package com.zxzh.shop.controller;


import com.zxzh.shop.dto.OrderDTO;
import com.zxzh.shop.exception.StockLackException;
import com.zxzh.shop.requestform.OrderForm;
import com.zxzh.shop.service.OrderService;
import com.zxzh.shop.util.ResponseBodyResultUtil;
import com.zxzh.shop.vo.PublicResponseBodyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderHandler {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PublicResponseBodyVO create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数错误,orderForm={}",orderForm);
            throw new StockLackException(bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getTel());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setSpecsId(orderForm.getSpecsId());
        orderDTO.setPhoneQuantity(orderForm.getQuantity());

        OrderDTO result = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());

        return ResponseBodyResultUtil.success(map);
    }

    @GetMapping("/detail/{orderId}")
    public PublicResponseBodyVO findOrderDetail(
            @PathVariable("orderId") String orderId){
        return ResponseBodyResultUtil.success(orderService.findOrderDetailByOrderId(orderId));
    }

    @PutMapping("/pay/{orderId}")
    public PublicResponseBodyVO pay(
            @PathVariable("orderId") String orderId){
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderService.pay(orderId));
        return ResponseBodyResultUtil.success(map);
    }

}
