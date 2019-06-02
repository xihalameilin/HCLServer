package com.example.demo.dao;

import com.example.demo.entity.Address;

import java.util.List;

public interface AddressDao {

    List<Address> getAllAddressesByUserID(Integer userID);

    void setDefaultAddress(Integer userID,Integer addressID);

}
