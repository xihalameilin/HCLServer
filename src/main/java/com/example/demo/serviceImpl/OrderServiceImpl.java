package com.example.demo.serviceImpl;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.UserDao;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;


    @Override
    public Integer insertOrder(Order order) {
       return orderDao.insertOrder(order);
    }

    @Override
    public void changeState(Integer orderID, Integer state) {
        //确认订单
        if(state ==2){
            Order order = orderDao.getOrderByOrderID(orderID);
            userDao.addPoint(order.getUserID(),order.getTotal());
            userDao.changeBalance(order.getUserID(),order.getTotal());
        }
        orderDao.changeState(orderID,state);
    }

    @Override
    public List<Order> getAllOrdersByState(Integer userID, Integer state) {
        return orderDao.getAllOrdersByState(userID,state);
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderID(Integer ID) {
        return orderDao.getAllOrderItems(ID);
    }

    @Override
    public List<Order> getAllOrdersByShopID(Integer shopID) {
        return orderDao.getAllOrdersByShopID(shopID);
    }



}
