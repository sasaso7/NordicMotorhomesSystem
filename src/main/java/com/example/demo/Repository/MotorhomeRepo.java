package com.example.demo.Repository;

import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeRepo {
    @Autowired
    JdbcTemplate template;

    // fetch all motorhomes metode
    public List<Motorhome> fetchAll() {
        String sql = "SELECT * FROM motorhomes";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<Motorhome>(Motorhome.class);
        return template.query(sql, rowMapper);
    }
    // add a motorhome metode
    public Motorhome add(Motorhome m) {
        String sql = "INSERT INTO motorHomes (motorHome_id, brand, model, odometer, seats, beds, fuel, price_per_day, size_id) VALUES (?, ?, ?, ?, ? ,?, ?, ?, ?)";
        template.update(sql, m.getMotorHome_id(), m.getBrand(), m.getModel(), m.getOdometer(), m.getSeats(), m.getBeds(), m.getFuel(), m.getPrice_per_day(), m.getSize_id());
        return null;
    }
    // create id for motorhome metode
    public int createID() {
        List<Motorhome> motorHomeList = fetchAll();
        Motorhome tempCar = motorHomeList.get(motorHomeList.size() - 1);
        if(tempCar.getMotorHome_id() == 1) {
            return 2;
        }else{
            return tempCar.getMotorHome_id() + 1;
        }
    }
    // find motorhome by id metode
    public Motorhome findById(int id){
        String sql ="SELECT * FROM motorHomes WHERE motorHome_id=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        Motorhome m = template.queryForObject(sql, rowMapper, id);
        return m;
    }
    // delete motorhome metode
    public Boolean delete(int id){
        String sql = "DELETE FROM motorHomes WHERE motorHome_id = ?";
        return template.update(sql, id) < 0;
    }
    // update motorhome metode
    public Motorhome update(Motorhome m){
        String sql = "UPDATE motorHomes SET brand = ?, model = ?, odometer = ?, seats = ?, beds = ?, fuel = ?, price_per_day = ?, size_id = ? WHERE motorHome_id = ?";
        template.update(sql, m.getBrand(), m.getModel(), m.getOdometer(), m.getSeats(), m.getBeds(), m.getFuel(), m.getPrice_per_day(), m.getSize_id() , m.getMotorHome_id());
        return null;
    }


}

