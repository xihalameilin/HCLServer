package com.example.demo.serviceImpl;

import com.example.demo.dao.ShopDao;
import com.example.demo.dao.UserDao;
import com.example.demo.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ShopDao shopDao;
    @Override
    public int getUserCount() {
        return userDao.getCount();
    }

    @Override
    public int getShopCount() {
        return shopDao.getCount();
    }

    @Override
    public double getTotal() {
        return shopDao.getTotal();
    }
}
