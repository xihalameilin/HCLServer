package com.example.demo.serviceImpl;

import com.example.demo.entity.User;
import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer login(String username, String password) {
        User user = userDao.getUserForLogin(username,password);
        if(null != user){
            return user.getId();
        }
        else {
            return -1;
        }
    }

    @Override
    public boolean isNameValid(String username) {
        List<User> users = userDao.getAllUsers();
        for(User user:users){
            if(user.getName().equals(username))
                return false;
        }
        return true;
    }

    @Override
    public boolean isEmailValid(String email) {
        List<User> users = userDao.getAllUsers();
        for(User user:users){
            if(user.getEmail().equals(email))
                return false;
        }
        return true;
    }

    @Override
    public User getUserByID(Integer userID) {
        return userDao.getUserByID(userID);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void insertUser(User user) {
        userDao.insert(user);
    }

    @Override
    public void addPoint(Integer userID, double point) {
        userDao.addPoint(userID,point);
    }


}
