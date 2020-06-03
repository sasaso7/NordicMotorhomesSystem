package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {
    @Id
    private String zip_code;
    private String city;

    public City(String zip_code, String city) {
        this.zip_code = zip_code;
        this.city = city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public City() {
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
