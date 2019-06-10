package org.newit.microservice.ebusiness.controller;

import org.newit.microservice.ebusiness.model.Order;
import org.newit.microservice.ebusiness.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController{

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/add/order")
    @ResponseBody
    public String addOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return "success";
    }

    @RequestMapping(value = "/order/{orderId}")
    @ResponseBody
    public Order orderDetail(@PathVariable("orderId") long orderId) {
        return orderService.getOrderById(orderId);
    }

    @RequestMapping("/order/buyerList")
    @ResponseBody
    public List<Order> buyerOrderList(@RequestParam("buyerId")long buyerId) {
        List<Order> orderList =  orderService.getOrderListByBuyerId(buyerId);
        return orderList;
    }

    @RequestMapping("/order/sellerList")
    @ResponseBody
    public List<Order> sellerOrderList(@RequestParam("sellerId")long sellerId) {
        List<Order> orderList =  orderService.getOrderListBySellerId(sellerId);
        return orderList;
    }
}
