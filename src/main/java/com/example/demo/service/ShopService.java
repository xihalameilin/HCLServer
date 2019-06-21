package com.example.demo.service;

import com.example.demo.entity.Shop;

import java.util.List;

public interface ShopService {
     String getShopID();

     List<Shop> getAllShops();

     void registerShop(Shop shop);

     Shop getShopByShopID(Integer shopID);

     List<Shop> getAllShopsByType(String type);

     List<Shop> getAllShopByKeyword(String keyword);
}
