package com.example.demo.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.RentalOffice;
import com.example.demo.Repository.RentalOfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalOfficeService {
    @Autowired
    com.example.demo.Repository.RentalOfficeRepo RentalOfficeRepo;

   public List<RentalOffice> fetchAll(){
       return RentalOfficeRepo.fetchAll();
   }
    public RentalOffice findByAddressId(int id){
       return RentalOfficeRepo.findByAddressId(id);
    }
    public RentalOffice add(RentalOffice r) {
        return RentalOfficeRepo.add(r);
    }

    public int createID() {
        return RentalOfficeRepo.createID();
    }
    public Boolean delete(int id){
        return RentalOfficeRepo.delete(id);
    }
}
