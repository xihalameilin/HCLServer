package com.example.demo.serviceImpl;

import com.example.demo.entity.Shop;
import com.example.demo.dao.ShopDao;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public String getShopID() {
        int count = shopDao.getAllShops().size()+1;
        String res = String.valueOf(count);
        int length = res.length();
        for(int i=1;i<=7-length;i++){
            res="0"+res;
        }
        return res;
    }

    @Override
    public List<Shop> getAllShops() {
        return shopDao.getAllShops();
    }

    @Override
    public void registerShop(Shop shop) {
        shopDao.insert(shop);
    }

    @Override
    public Shop getShopByShopID(Integer shopID) {
        return shopDao.getShopByShopID(shopID);
    }

}
