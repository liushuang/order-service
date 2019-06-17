package org.newit.microservice.ebusiness.service;

import org.newit.microservice.ebusiness.config.OrderDiscountConfig;
import org.newit.microservice.ebusiness.model.Order;
import org.newit.microservice.ebusiness.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public void createOrder(Order order) {
        order.setPrice(new Double(Math.ceil(order.getPrice() * OrderDiscountConfig.DISCOUNT)).longValue());
        orderRepository.insert(order);
    }

    public List<Order> getOrderListByBuyerId(long buyerId) {
        return orderRepository.getOrderListByBuyerId(buyerId);
    }

    public List<Order> getOrderListBySellerId(long sellerId) {
        return orderRepository.getOrderListBySellerId(sellerId);
    }

}
