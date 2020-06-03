package com.example.demo.Repository;

import com.example.demo.Model.Extra;
import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtraRepo {
    @Autowired
    JdbcTemplate template;

    // fetch all extras metode
    public List<Extra> fetchAll(){
        String sql = "SELECT * FROM extras";
        RowMapper<Extra> rowMapper = new BeanPropertyRowMapper<Extra>(Extra.class);
        return template.query(sql, rowMapper);
    }
    // add extras metode
    public Extra add(Extra e) {
        String sql = "INSERT INTO extras (extra_id, picnic_tables, beds, chairs, bike_racks, child_seats) VALUES (?, ?, ?, ?, ? , ?)";
        template.update(sql, createID(), e.getPicnic_tables(), e.getBeds(), e.getChairs(), e.getBike_racks(), e.getChild_seats());
        return null;
    }
    // create id for extras metode
    public int createID() {
        List<Extra> extraList = fetchAll();
        Extra tempExtra = extraList.get(extraList.size() - 1);
        if(extraList.size() == 1)
            return 2;
        else
            return tempExtra.getExtra_id() + 1;
    }
    // find extras by id metode
    public Extra findByID(int id){
        String sql = "SELECT * FROM extras WHERE extra_id = ?";
        RowMapper<Extra> rowMapper = new BeanPropertyRowMapper<>(Extra.class);
        Extra e = template.queryForObject(sql, rowMapper, id);
        return e;
    }
}
