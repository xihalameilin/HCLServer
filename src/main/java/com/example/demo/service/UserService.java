package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

     Integer login(String username, String password);

     boolean isNameValid(String  username);

     boolean isEmailValid(String email);

     User getUserByID(Integer userID);

     void updateUser(User user);

     void insertUser(User user);

     void addPoint(Integer userID, double point);
}
