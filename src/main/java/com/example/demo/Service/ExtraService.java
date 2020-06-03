package com.example.demo.Service;

import com.example.demo.Model.Extra;
import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraService {
    @Autowired
    com.example.demo.Repository.ExtraRepo ExtraRepo;


    public List<Extra> fetchAll(){ return ExtraRepo.fetchAll(); }
    public Extra add(Extra e){
        return ExtraRepo.add(e);
    }
    public int createID(){
        return ExtraRepo.createID();
    }
    public Extra findByID(int id){ return ExtraRepo.findByID(id);
    }
}
