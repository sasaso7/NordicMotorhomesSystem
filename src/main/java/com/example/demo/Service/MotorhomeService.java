package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import com.example.demo.Repository.MotorhomeRepo;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeService {
    @Autowired
    MotorhomeRepo Motorhomerepo;

    public List<Motorhome> fetchAll(){
        return Motorhomerepo.fetchAll();

    }

    public Motorhome add(Motorhome m){
        return Motorhomerepo.add(m);
    }

    public int createID() {
        return Motorhomerepo.createID();
    }
    public Motorhome findById(int id){
        return Motorhomerepo.findById(id);
    }
    public Boolean delete(int id){
        return Motorhomerepo.delete(id);
    }
    public Motorhome update(Motorhome m){
        return Motorhomerepo.update(m);
    }

}
