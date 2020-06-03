package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    com.example.demo.Repository.EmployeeRepo EmployeeRepo;

    public List<Employee> fetchAll(){
        return EmployeeRepo.fetchAll();
    }
    public Employee add(Employee e) {
        return EmployeeRepo.add(e);
    }

    public int createID() {
        return EmployeeRepo.createID();
    }
    public Employee findById(int id){
        return EmployeeRepo.findById(id);
    }
    public Boolean delete(int id){
        return EmployeeRepo.delete(id);
    }
    public Employee update(Employee e){
        return EmployeeRepo.update(e);
    }

}
