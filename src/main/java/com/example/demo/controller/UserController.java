package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;


/**
 * 与这个controller相关的对象为 User Address
 */
@RestController
@RequestMapping("/UserController")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private EmailService emailService;

    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 根据userID得到User对象
     */
    @GetMapping("/getUser/{userID}")
    public String getUserByUserID(@PathVariable Integer userID){
        return new JSONObject(userService.getUserByID(userID)).toString();
    }

    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 登录
     * @param username
     * @param password
     * @res   返回user的ID值 正常会>0  失败返回-1
     */
    @GetMapping("/login/{username}/{password}")
    public int login(@PathVariable("username")String username, @PathVariable("password")String password){
        return userService.login(username,password);
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 注册    亲测有效 state 表示是否是默认的 暂时定1为默认？？？？
     * json字符串如下
     *{
        "name":"lml",
        "password":"123",
        "email":"1293086146@qq.com"
        }
     */
    @PostMapping("/userRegister")
    public void registerUser(@RequestBody User user){
        userService.insertUser(user);
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 用户名是否可用
     */
    @GetMapping("/CheckUserID/{username}")
    public boolean isUserIDValid(@PathVariable String username){
        return userService.isNameValid(username);
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 邮箱是否可用
     *@res   可用为true
     */
    @GetMapping("/CheckEmail/{email}")
    public boolean isEmailIDValid(@PathVariable String email){
        return userService.isEmailValid(email);
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 发邮件
     * @param email
     * @param content   验证码
     */
    @GetMapping("/sendMail/{email}/{content}")
    public void sendIdentityCode(@PathVariable String email,@PathVariable String content){
        try {
            emailService.sendEmail(content,email);
        } catch (Exception e) {
            logger.error("发送邮件爆炸了,兄弟");
            e.printStackTrace();
        }
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 更新邮箱  应该用不到
     */
    @PostMapping("/update/{userID}/{email}/")
    public void update(@PathVariable Integer userID,
                       @PathVariable String email){
        User user = userService.getUserByID(userID);
        user.setEmail(email);
        userService.updateUser(user);
    }



    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 根据userID得到所有的地址
     */
    @GetMapping("/getAllAddresses/{userID}")
    public String getAllAddressesByUserID(@PathVariable Integer userID){
        return new JSONArray(addressService.getAllAddressesByUserID(userID)).toString();
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 设置默认的地址
     */
    @PostMapping("/setDefaultAddress/{userID}/{addressID}")
    public void setDefaultAddress(@PathVariable Integer userID,@PathVariable Integer addressID){
        addressService.setDefaultAddress(userID,addressID);
    }


    /**
     *@Author : LML
     *@Date : 21:40 2019/6/2
     *@Desciption : 根据 userID 与 address新增一个地址
     */
    @PostMapping("/addAddress/{userID}/{address}")
    public void addAddress(@PathVariable Integer userID,@PathVariable String address){
        User user = userService.getUserByID(userID);
        Address add = new Address();
        add.setAddress(address);
        add.setUser(user);
        add.setState(0);
        addressService.insertAddress(add);
    }


    @PostMapping("/deleteAddress/{addressID}")
    public void deleteAddress(@PathVariable Integer addressID){
        addressService.deleteAddress(addressID);
    }

}
