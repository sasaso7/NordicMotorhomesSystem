package com.example.demo.Repository;

import com.example.demo.Model.Address;
import com.example.demo.Model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepo {
    @Autowired
    JdbcTemplate template;

    //fetch all cities metode
    public List<City> fetchAll(){
        String sql = "SELECT * FROM cities";
        RowMapper<City> rowMapper = new BeanPropertyRowMapper<City>(City.class);
        return template.query(sql, rowMapper);
    }

    public City findById(int id) {
        String sql = "SELECT * FROM cities WHERE zip_code=?";
        RowMapper<City> rowMapper = new BeanPropertyRowMapper<>(City.class);
        City c = template.queryForObject(sql, rowMapper, id);
        return c;
    }
}
