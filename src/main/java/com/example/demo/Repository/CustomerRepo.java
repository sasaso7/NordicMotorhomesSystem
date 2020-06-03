package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    // fetch all customers metode
    public List<Customer> fetchAll(){
        String sql = "SELECT * FROM customers";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class);
        return template.query(sql, rowMapper);
    }
    // add customer metode
    public Customer add(Customer c) {
        String sql = "INSERT INTO customers (customer_id, first_name, last_name, email, create_date, address_id) VALUES (?, ?, ?, ?, ? ,?)";
        template.update(sql, c.getCustomer_id(), c.getFirst_name(), c.getLast_name(), c.getEmail(), c.getCreate_date(), c.getAddress_id());
        return null;
    }
    // create id for customer metode
    public int createID() {
        List<Customer> customerList = fetchAll();
        Customer tempCar = customerList.get(customerList.size() - 1);
        if(tempCar.getCustomer_id() == 1) {
            return 2;
        }else{
            return tempCar.getCustomer_id() + 1;
        }
    }
    // find customer by id metode
    public Customer findById(int id){
        String sql ="SELECT * FROM customers WHERE customer_id=?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sql, rowMapper, id);
        return c;
    }
    // delete customer metode
    public Boolean delete(int id){
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        return template.update(sql, id) < 0;
    }
    // update customer metode
    public Customer update(Customer c){
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, address_id = ? WHERE customer_id = ?";
        template.update(sql, c.getFirst_name(), c.getLast_name(), c.getEmail(), c.getAddress_id(), c.getCustomer_id());
        return null;
    }
}
