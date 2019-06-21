package com.example.demo.dao;

import com.example.demo.entity.Shop;

import java.util.List;

public interface ShopDao extends BaseDao {

     List<Shop> getAllShops();

     Shop getShopByShopID(Integer shopID);

     int getCount();

     double getTotal();

     List<Shop> getAllShopsByType(String type);

     List<Shop> getAllShopByKeyword(String keyword);
}
