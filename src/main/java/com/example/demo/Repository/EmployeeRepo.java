package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepo {
    @Autowired
    JdbcTemplate template;

    //fetch all employees metode
    public List<Employee> fetchAll(){
        String sql = "SELECT * FROM employees";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
        return template.query(sql, rowMapper);
    }
    // add employee metode
    public Employee add(Employee e) {
        String sql = "INSERT INTO employees (employee_id, first_name, last_name, email, employee_status, profession, address_id, rental_office_id, password_emp) VALUES (?, ?, ?, ?, ? ,?, ?, ?, ?)";
        template.update(sql, e.getEmployee_id(), e.getFirst_name(), e.getLast_name(), e.getEmail(), e.getEmployee_status(), e.getProfession(), e.getAddress_id(), e.getRental_office_id(), e.getPassword_emp());
        return null;
    }

    // create id for employee metode
    public int createID() {
        List<Employee> employeeList = fetchAll();
        Employee tempCar = employeeList.get(employeeList.size() - 1);
        if(tempCar.getEmployee_id() == 1) {
            return 2;
        }else{
            return tempCar.getEmployee_id() + 1;
        }
    }
    // find employee by id metode
    public Employee findById(int id){
        String sql ="SELECT * FROM employees WHERE employee_id=?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee e = template.queryForObject(sql, rowMapper, id);
        return e;
    }
    // delete employee metode
    public Boolean delete(int id){
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        return template.update(sql, id) < 0;
    }
    // update employee metode
    public Employee update(Employee e){
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email =?, employee_status = ?, profession = ?, address_id = ?, rental_office_id = ?, password_emp = ? WHERE employee_id = ?";
        template.update(sql, e.getFirst_name(), e.getLast_name(), e.getEmail(), e.getEmployee_status(), e.getProfession(), e.getAddress_id(), e.getRental_office_id(), e.getPassword_emp(), e.getEmployee_id());
        return null;
    }
}
