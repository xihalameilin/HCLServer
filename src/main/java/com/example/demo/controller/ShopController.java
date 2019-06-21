package com.example.demo.controller;


import com.example.demo.service.DishService;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 与这个controller相关的对象为 Shop Dish  (店铺与菜品)
 */
@RestController
@RequestMapping("/ShopController")
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private DishService dishService;


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 得到所有商店
     */
    @GetMapping("/getAll")
    public String getAllShops(){
        return new org.json.JSONArray(shopService.getAllShops()).toString();
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 根据shopID得到具体的菜品
     */
    @GetMapping("/getAllDishes/{shopID}")
    public String getAllDishesByShopID(@PathVariable Integer shopID){
        return new org.json.JSONArray(dishService.getAllDishesByShopID(shopID)).toString();
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 根据类型得到所有的店铺
     */
    @GetMapping("/getAllShopsByType/{type}")
    public String getAllShopsByType(@PathVariable String type){
        return new org.json.JSONArray(shopService.getAllShopsByType(type)).toString();
    }

    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 搜索店铺
     */
    @GetMapping("/getAllShopsByKeyword/{keyword}")
    public String getAllShopsByKeyword(@PathVariable String keyword){
        return new org.json.JSONArray(shopService.getAllShopByKeyword(keyword)).toString();
    }

}
