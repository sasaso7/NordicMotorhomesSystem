package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee extends Person {
    @Id
    private int employee_id;
    private int employee_status; // make boolean
    private String profession;
    private int address_id;
    private int rental_office_id;


    private String password_emp;

    public Employee() {

    }

    public Employee(int employee_id, String first_name, String last_name, String email, int employee_status, String profession, int address_id, int rental_office_id, String password_emp) {
        super(first_name, last_name, email);
        this.employee_id = employee_id;
        this.employee_status = employee_status;
        this.profession = profession;
        this.address_id = address_id;
        this.rental_office_id = rental_office_id;
        this.password_emp = password_emp;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getEmployee_status() {
        return employee_status;
    }

    public void setEmployee_status(int employee_status) {
        this.employee_status = employee_status;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getRental_office_id() {
        return rental_office_id;
    }

    public void setRental_office_id(int rental_office_id) {
        this.rental_office_id = rental_office_id;
    }

    public String getPassword_emp() {
        return password_emp;
    }

    public void setPassword_emp(String password_emp) {
        this.password_emp = password_emp;
    }

}