package com.example.demo.serviceImpl;

import com.example.demo.entity.Address;
import com.example.demo.dao.AddressDao;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    @Autowired
    private AddressDao addressDao;
    @Override
    public List<Address> getAllAddressesByUserID(Integer userID) {
        return addressDao.getAllAddressesByUserID(userID);
    }


    @Override
    public void setDefaultAddress(Integer userID, Integer addressID) {
        addressDao.setDefaultAddress(userID,addressID);
    }

    @Override
    public void insertAddress(Address address) {
        addressDao.insert(address);
    }

    @Override
    public void deleteAddress(Integer addressID) {
        addressDao.delete(addressID);
    }
}
