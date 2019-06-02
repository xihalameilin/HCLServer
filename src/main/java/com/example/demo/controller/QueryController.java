package com.example.demo.controller;


import com.example.demo.service.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 *@Author : LML
 *@Date : 21:40 2019/6/2
 *@Desciption : 整个controller的职责已经分到其他三个controller 暂时废弃掉
 */
@RestController
@RequestMapping("/QueryController")
public class QueryController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private DishService dishService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/getAll")
    public String getAllShops(){
        return new JSONArray(shopService.getAllShops()).toString();
    }


    @GetMapping("/getAllDishes/{shopID}")
    public String getAllDishesByShopID(@PathVariable Integer shopID){
        return new JSONArray(dishService.getAllDishesByShopID(shopID)).toString();
    }

    @GetMapping("/getAllAddresses/{userID}")
    public String getAllAddressesByUserID(@PathVariable Integer userID){
        return new JSONArray(addressService.getAllAddressesByUserID(userID)).toString();
    }


    @GetMapping("/getAllOrders/{userID}/{state}")
    public String getAllOrdersByState(@PathVariable Integer userID,@PathVariable Integer state){
        return new JSONArray(orderService.getAllOrdersByState(userID,state)).toString();
    }

    @GetMapping("/getAllOrdersByShopID/{shopID}")
    public String getAllOrdersByShopID(@PathVariable Integer shopID){
        return new JSONArray(orderService.getAllOrdersByShopID(shopID)).toString();
    }

    @GetMapping("/getAllOrderItems/{orderID}")
    public String getAllOrdersByState(@PathVariable Integer orderID){
        return new JSONArray(orderService.getAllOrderItemsByOrderID(orderID)).toString();
    }

    @GetMapping("/getUser/{userID}")
    public String getUserByUserID(@PathVariable Integer userID){
        return new JSONObject(userService.getUserByID(userID)).toString();
    }


    @GetMapping("/getdata")
    public String getdata(){
        int userCount = analysisService.getUserCount();
        int shopCount = analysisService.getShopCount();
        double total = analysisService.getTotal();
        return userCount+" "+shopCount+" "+total;
    }

}
