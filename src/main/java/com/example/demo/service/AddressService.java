package com.example.demo.service;

import com.example.demo.entity.Address;

import java.util.List;

public interface AddressService {
     List<Address> getAllAddressesByUserID(Integer userID);

     void setDefaultAddress(Integer userID,Integer addressID);

     void insertAddress(Address address);

     void deleteAddress(Integer addressID);
}
