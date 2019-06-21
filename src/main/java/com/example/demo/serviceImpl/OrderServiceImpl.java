package com.example.demo.serviceImpl;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.UserDao;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    /**
     * @Author : gsy
     * @param userID
     * @return得到3个月的订单
     */
    @Override
    public List<Order> getThreeMonths(int userID) {
        ArrayList<Order> orderArrayList = (ArrayList)orderDao.getAllOrdersByUserID(userID);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date dt1 = new Date();
        Date dt2 = new Date();
        ArrayList<Order> resultlists=new ArrayList<>();
        dt2.setMonth(dt2.getMonth()-3);
        for (int i =0 ;i < orderArrayList.size();i++){
            Order o = orderArrayList.get(i);
            if ( o.getDate().getTime()<=dt1.getTime()&&dt2.getTime()<=o.getDate().getTime()){
                resultlists.add(o);
            }
        }
        return resultlists;
    }



}
