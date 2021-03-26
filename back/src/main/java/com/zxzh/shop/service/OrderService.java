package com.zxzh.shop.service;

import com.zxzh.shop.dto.OrderDTO;
import com.zxzh.shop.vo.order.OrderDetailVO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public OrderDTO create(OrderDTO orderDTO);
    public OrderDetailVO findOrderDetailByOrderId(String orderId);
    public String pay(String orderId);
}
