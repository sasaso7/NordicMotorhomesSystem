package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RentalOffice {
    @Id
    private int rental_office_id;
    private int address_id;

    public RentalOffice(){

    }
    public RentalOffice(int rental_office_id, int addressID) {
        this.rental_office_id = rental_office_id;
        this.address_id = addressID;
    }

    public int getRental_office_id() {
        return rental_office_id;
    }

    public void setRental_office_id(int rental_office_id) {
        this.rental_office_id = rental_office_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int addressID) {
        this.address_id = addressID;
    }
}
