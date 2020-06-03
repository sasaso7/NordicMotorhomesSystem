package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.RentalOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalOfficeRepo {
    @Autowired
    JdbcTemplate template;

    // fetch all rental offices metode
    public List<RentalOffice> fetchAll(){
        String sql = "SELECT * FROM rentalOffices";
        RowMapper<RentalOffice> rowMapper = new BeanPropertyRowMapper<>(RentalOffice.class);
        return template.query(sql, rowMapper);
    }
    // add a rental office metode
    public RentalOffice add(RentalOffice r){
        String sql ="INSERT INTO rentalOffices(rental_office_id, address_id) VALUES(?, ?)";
        template.update(sql, r.getRental_office_id(), r.getAddress_id());
        return null;
    }
    // find rental office by address id metode
    public RentalOffice findByAddressId(int id){ //Specifik metode til CreateEmployee
        String sql = "SELECT * FROM rentalOffices WHERE address_id = ?";
        RowMapper<RentalOffice> rowMapper = new BeanPropertyRowMapper<>(RentalOffice.class);
        RentalOffice r = template.queryForObject(sql, rowMapper, id);
        return r;
    }
    // create rental office id metode
    public int createID() {
        List<RentalOffice> rentalOfficeList = fetchAll();
        RentalOffice tempCar = rentalOfficeList.get(rentalOfficeList.size() - 1);
        if(tempCar.getRental_office_id() == 1) {
            return 2;
        }else{
            return tempCar.getRental_office_id() + 1;
        }
    }
    // delete rental office metode
    public Boolean delete(int id){
        String sql = "DELETE FROM rentalOffices WHERE rental_office_id = ?";
        return template.update(sql, id) < 0;
    }

}

