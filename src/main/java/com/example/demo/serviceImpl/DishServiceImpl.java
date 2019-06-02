package com.example.demo.serviceImpl;

import com.example.demo.entity.Dish;
import com.example.demo.dao.DishDao;
import com.example.demo.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishDao dishDao;
    @Override
    public List<Dish> getAllDishesByShopID(Integer shopID) {
        return dishDao.getAllDishByShopID(shopID);
    }
}
