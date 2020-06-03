package com.example.demo.Service;

import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    com.example.demo.Repository.SizeRepo SizeRepo;

    public List<Size> fetchAll(){
        return SizeRepo.fetchAll();
    }
    public int createID(){
        return SizeRepo.createID();
    }
    public Size add(Size s){
      return SizeRepo.add(s);
    }
    public Boolean delete(int id){
        return SizeRepo.delete(id);
    }
    public Size update(Size s){
        return SizeRepo.update(s);
    }
    public Size findByID(int id){
        return SizeRepo.findByID(id);
    }
}