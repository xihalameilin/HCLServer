package com.example.demo.dao;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

import java.util.List;

public interface OrderDao {

     Integer insertOrder(Order order);

     void changeState(Integer orderID, Integer state);

     List<Order> getAllOrdersByState(Integer userID, Integer state);

     List<OrderItem> getAllOrderItems(Integer ID);

     Order getOrderByOrderID(Integer orderID);

     List<Order> getAllOrdersByShopID(Integer shopID);

     public List<Order> getAllOrdersByUserID(int userID);
}
