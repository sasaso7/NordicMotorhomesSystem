package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
     @Id
    private int address_id;
    private String address_1;
    private int phone;
    private int zip_code;

    public Address() {
    }

    public Address(int address_id, String address_1, int phone, int zip_code) {
        this.address_id = address_id;
        this.address_1 = address_1;
        this.phone = phone;
        this.zip_code = zip_code;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

}
