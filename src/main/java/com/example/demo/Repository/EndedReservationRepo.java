package com.example.demo.Repository;

import com.example.demo.Model.EndedReservation;
import com.example.demo.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class EndedReservationRepo {

    @Autowired
    JdbcTemplate template;

    // fetch all ended reservations metode
        public List<EndedReservation> fetchAll(){
        String sql = "SELECT * FROM endedReservations";
        RowMapper<EndedReservation> rowMapper = new BeanPropertyRowMapper<EndedReservation>(EndedReservation.class);
        return template.query(sql, rowMapper);
    }
    // add ended reservations metode
    public EndedReservation add(EndedReservation r) {
        String sql = "INSERT INTO endedReservations (endedReservation_id, reservation_id, rental_office_id, billing_date, completed, km_driven, extra_repair, fuel_tank, droppoint, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, r.getEndedReservation_id(), r.getReservation_id(), r.getRental_office_id(), r.getBilling_date(), r.getCompleted(), r.getKm_driven(), r.getExtra_repair(), r.getFuel_tank(), r.getDroppoint(), r.getTotal_price());
        return null;
    }
    // create id for ended reservations
    public int createID() {
        List<EndedReservation> endedReservationList = fetchAll();
        EndedReservation tempCar = endedReservationList.get(endedReservationList.size() - 1);
        if(endedReservationList.size() == 1)
            return 2;
        else
            return tempCar.getEndedReservation_id() + 1;
    }
}
