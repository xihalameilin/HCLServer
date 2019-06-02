package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

import java.util.List;

public interface OrderService {
     Integer insertOrder(Order order);

     void changeState(Integer orderID, Integer state);

     List<Order> getAllOrdersByState(Integer userID, Integer state);

     List<OrderItem> getAllOrderItemsByOrderID(Integer ID);

     List<Order> getAllOrdersByShopID(Integer shopID);

}
