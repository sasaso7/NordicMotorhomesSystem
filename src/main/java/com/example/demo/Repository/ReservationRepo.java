package com.example.demo.Repository;

import com.example.demo.Model.RentalOffice;
import com.example.demo.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepo {
    @Autowired
    JdbcTemplate template;

    // fetch all reservations metode
    public List<Reservation> fetchAll(){
        String sql = "SELECT * FROM reservations";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<Reservation>(Reservation.class);
        return template.query(sql, rowMapper);
    }
    // add a reservation metode
    public Reservation add(Reservation r) {
        String sql = "INSERT INTO reservations (reservation_id, start_date, end_date, creation_date, extra_id, employee_id, customer_id, motorHome_id, rental_office_id, droppoint) VALUES (?, ?, ?, ?, ? ,?, ?, ?, ?, ?)";
        template.update(sql, createID(), r.getStart_date(), r.getEnd_date(), r.getCreation_date(), r.getExtra_id(), r.getEmployee_id(), r.getCustomer_id(), r.getMotorHome_id(), r.getRental_office_id(), r.getDroppoint());
        return null;
    }
    // find reservations by id metode
    public Reservation findByID(int id){
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        Reservation r = template.queryForObject(sql, rowMapper, id);
        return r;
    }
    // delete reservations metode
    public Boolean delete(int id){
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        return template.update(sql, id) < 0;
    }
    // create id for reservations metode
    public int createID() {
        List<Reservation> reservationList = fetchAll();
        Reservation tempCar = reservationList.get(reservationList.size() - 1);
        if(reservationList.size() == 1)
            return 2;
        else
            return tempCar.getReservation_id() + 1;
    }
}

