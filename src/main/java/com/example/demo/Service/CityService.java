package com.example.demo.Service;

import com.example.demo.Model.City;
import com.example.demo.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    com.example.demo.Repository.CityRepo CityRepo;

    public List<City> fetchAll() {
        return CityRepo.fetchAll();
    }
    public City findById(int id){
        return CityRepo.findById(id);
    }


}
