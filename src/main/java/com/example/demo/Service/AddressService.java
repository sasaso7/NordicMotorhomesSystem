package com.example.demo.Service;

import com.example.demo.Model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    com.example.demo.Repository.AddressRepo AddressRepo;


    public List<Address> fetchAll() {
        return AddressRepo.fetchAll();
    }
    public Address add(Address a){
       return AddressRepo.add(a);
    }
    public int createID(){
        return AddressRepo.createID();
    }
    public Address findById(int i){
        return AddressRepo.findById(i);
    }
    public Address update(Address a){
        return AddressRepo.update(a);
    }
}
