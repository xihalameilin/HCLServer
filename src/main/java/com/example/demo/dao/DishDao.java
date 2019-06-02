package com.example.demo.dao;


import com.example.demo.entity.Dish;

import java.util.List;

public interface DishDao {
     List<Dish> getAllDishByShopID(Integer shopID);
}
