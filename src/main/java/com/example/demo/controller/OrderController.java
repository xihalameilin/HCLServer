package com.example.demo.controller;


import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.service.OrderService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;



/**
 * 与这个controller相关的对象为 Order OrderItem
 */
@RestController
@RequestMapping("/OrderController")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 根据userID和state得到所有的order
     * @param userID
     * @param state
     */
    @GetMapping("/getAllOrders/{userID}/{state}")
    public String getAllOrdersByState(@PathVariable Integer userID,@PathVariable Integer state){
        return new JSONArray(orderService.getAllOrdersByState(userID,state)).toString();
    }

    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 根据orderID得到所有的orderItem
     * @param orderID
     */
    @GetMapping("/getAllOrderItems/{orderID}")
    public String getAllOrdersByState(@PathVariable Integer orderID){
        return new JSONArray(orderService.getAllOrderItemsByOrderID(orderID)).toString();
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 新增订单  这里可以试一下 @RequestBody 注解  讨论一下 这里json有点复杂  状态?
     *@res   返回创建order的ID
     * {
        "shopID":"1",
        "userID":"1",
        "address":"nju",
        "total":14,
        "orderItems":[
        {"name":"娃娃菜","price":5,"num":2,"total":10},
        {"name":"腐竹","price":2,"num":2,"total":4}
        ]
        }
     */
    @PostMapping("/insertOrder")
    public Integer insertOrder(@RequestBody Order order){
        order.setDate(new Date());
        order.setState(0);
        Set<OrderItem> orderItems = order.getOrderItems();
        for(OrderItem orderItem:orderItems) {
            orderItem.setOrder(order);
        }
        return  orderService.insertOrder(order);
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 改变订单状态   后续的逻辑可能还要补上  状态设定？？？  0未支付  1放弃  2支付成功
     * @param state
     */
    @PostMapping("/changeState/{orderID}/{state}")
    public void changeState(@PathVariable Integer orderID,@PathVariable Integer state){
        orderService.changeState(orderID,state);
    }

}
