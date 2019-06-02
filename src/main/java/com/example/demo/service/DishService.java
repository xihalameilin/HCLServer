package com.example.demo.service;

import com.example.demo.entity.Dish;

import java.util.List;

public interface DishService {

    public List<Dish> getAllDishesByShopID(Integer shopID);
}
