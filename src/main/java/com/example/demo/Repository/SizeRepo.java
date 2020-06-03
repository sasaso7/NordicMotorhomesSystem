package com.example.demo.Repository;

import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Reservation;
import com.example.demo.Model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SizeRepo {
    @Autowired
    JdbcTemplate template;

    // fetch all sizes metode
    public List<Size> fetchAll(){
        String sql = "SELECT * FROM sizes";
        RowMapper<Size> rowMapper = new BeanPropertyRowMapper<Size>(Size.class);
        return template.query(sql, rowMapper);
    }
    // add a size metode
    public Size add(Size s){
        String sql = "INSERT INTO sizes (size_id, length, height, weight) VALUES (?, ?, ?, ?)";
        template.update(sql,s.getSize_id(), s.getLength(), s.getHeight(), s.getWeight());
        return null;
    }
    // create id for sizes metode
    public int createID() {
        List<Size> SizeList = fetchAll();
        Size tempSize = SizeList.get(SizeList.size() - 1);
            return (tempSize.getSize_id() + 1);
    }
    // delete size metode
    public Boolean delete(int id){
        String sql = "DELETE FROM sizes WHERE size_id = ?";
        return template.update(sql, id) < 0;
    }
    // update size metode
    public Size update(Size s){
        String sql = "UPDATE sizes SET length = ?, height = ?, weight = ? WHERE size_id = ?";
        template.update(sql, s.getLength(), s.getHeight(), s.getWeight() , s.getSize_id());
        return null;
    }
    // find size by id metode
    public Size findByID(int id){
        String sql = "SELECT * FROM sizes WHERE size_id = ?";
        RowMapper<Size> rowMapper = new BeanPropertyRowMapper<>(Size.class);
        Size s = template.queryForObject(sql, rowMapper, id);
        return s;
    }
}

