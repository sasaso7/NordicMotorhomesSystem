package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Extra {
    //Bike rack beds, child seat, picnictable, chairs
    @Id
    private int extra_id;
    private int picnic_tables;
    private int beds;
    private int chairs;
    private int bike_racks;
    private int child_seats;

    public Extra(){

    }
    public Extra(int extra_id, int picnic_tables, int beds, int chairs, int bike_racks, int child_seats) {
        this.extra_id = extra_id;
        this.picnic_tables = picnic_tables;
        this.beds = beds;
        this.chairs = chairs;
        this.bike_racks = bike_racks;
        this.child_seats = child_seats;
    }

    public int getExtra_id() {
        return extra_id;
    }

    public void setExtra_id(int extra_id) {
        this.extra_id = extra_id;
    }

    public int getPicnic_tables() {
        return picnic_tables;
    }

    public void setPicnic_tables(int picnic_tables) {
        this.picnic_tables = picnic_tables;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public int getBike_racks() {
        return bike_racks;
    }

    public void setBike_racks(int bike_racks) {
        this.bike_racks = bike_racks;
    }

    public int getChild_seats() {
        return child_seats;
    }

    public void setChild_seats(int child_seats) {
        this.child_seats = child_seats;
    }
}
