package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;

public interface UserDao extends BaseDao{


     User getUserForLogin(String username, String password);

     List<User> getAllUsers();

     User getUserByID(Integer userID);


     void addPoint(Integer userID, double point);

     void changeBalance(Integer userID, double total);

     int getCount();
}
