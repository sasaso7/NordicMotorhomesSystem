package com.example.demo.Service;

import com.example.demo.Model.EndedReservation;
import com.example.demo.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndedReservationService {
    @Autowired
    com.example.demo.Repository.EndedReservationRepo endedReservationRepo;

    public List<EndedReservation> fetchAll(){
        return endedReservationRepo.fetchAll();
    }
    public EndedReservation add(EndedReservation e){
        return endedReservationRepo.add(e);
    }
    public int createID(){
        return endedReservationRepo.createID();
    }
}
