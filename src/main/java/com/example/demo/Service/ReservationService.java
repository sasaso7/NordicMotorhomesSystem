package com.example.demo.Service;

import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Reservation;
import com.example.demo.Repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    com.example.demo.Repository.ReservationRepo ReservationRepo;

    public List<Reservation> fetchAll(){
        return ReservationRepo.fetchAll();
    }
    public Reservation add(Reservation r){
        return ReservationRepo.add(r);
    }
    public boolean delete(int id){
        return ReservationRepo.delete(id);
    }
    public Reservation findByID(int id){ return ReservationRepo.findByID(id);
    }

}
