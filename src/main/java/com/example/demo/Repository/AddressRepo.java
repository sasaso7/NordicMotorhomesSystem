package com.example.demo.Repository;

import com.example.demo.Model.Address;
import com.example.demo.Model.Address;
import com.example.demo.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepo {
    @Autowired
    JdbcTemplate template;

    // fetch all metode
    public List<Address> fetchAll(){
        String sql = "SELECT * FROM address";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<Address>(Address.class);
        return template.query(sql, rowMapper);
    }
    // add address metode
    public Address add(Address c) {
        String sql = "INSERT INTO address (address_id, address_1, phone, zip_code) VALUES (?, ?, ?, ?)";
        template.update(sql, c.getAddress_id(), c.getAddress_1(), c.getPhone(), c.getZip_code());
        return null;
    }
    // find address by id metode
    public Address findById(int id) {
        String sql = "SELECT * FROM address WHERE address_id=?";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);
        Address a = template.queryForObject(sql, rowMapper, id);
        return a;
    }
    // create id for address metode
    public int createID() {
        List<Address> addressList = fetchAll();
        Address tempCar = addressList.get(addressList.size() - 1);
        if(tempCar.getAddress_id() == 1) {
            return 2;
        }else{
            return tempCar.getAddress_id() + 1;
        }
    }
    // update address metode
    public Address update(Address a){
        String sql = "UPDATE address SET address_1 = ?, phone = ?, zip_code = ? WHERE address_id = ?";
        template.update(sql, a.getAddress_1(), a.getPhone(), a.getZip_code(), a.getAddress_id());
        return null;
    }
}
