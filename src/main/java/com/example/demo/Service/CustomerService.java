package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    com.example.demo.Repository.CustomerRepo CustomerRepo;

    public List<Customer> fetchAll() {
        return CustomerRepo.fetchAll();
    }

    public Customer add(Customer c) {
        return CustomerRepo.add(c);
    }

    public int createID() {
        return CustomerRepo.createID();
    }
    public Customer findById(int id){
        return CustomerRepo.findById(id);
    }
    public Boolean delete(int id){
        return CustomerRepo.delete(id);
    }
    public Customer update(Customer c){
        return CustomerRepo.update(c);
    }

}
