package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class EndedReservation {
    @Id
    private int endedReservation_id;
    private int reservation_id;
    private int rental_office_id;
    private Date billing_date;
    private int completed;
    private int km_driven;
    private int extra_repair;
    private int fuel_tank;
    private int droppoint;
    private int total_price;

    public EndedReservation() {
    }

    public EndedReservation(int endedReservation_id, int reservation_id, int rental_office_id, Date billing_date, int completed, int km_driven, int extra_repair, int fuel_tank, int droppoint, int total_price) {
        this.endedReservation_id = endedReservation_id;
        this.reservation_id = reservation_id;
        this.rental_office_id = rental_office_id;
        this.billing_date = billing_date;
        this.completed = completed;
        this.km_driven = km_driven;
        this.extra_repair = extra_repair;
        this.fuel_tank = fuel_tank;
        this.droppoint = droppoint;
        this.total_price = total_price;
    }

    public int getExtra_repair() {
        return extra_repair;
    }

    public void setExtra_repair(int extra_repair) {
        this.extra_repair = extra_repair;
    }

    public int getEndedReservation_id() {
        return endedReservation_id;
    }

    public void setEndedReservation_id(int endedReservation_id) {
        this.endedReservation_id = endedReservation_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getRental_office_id() {
        return rental_office_id;
    }

    public void setRental_office_id(int rental_office_id) {
        this.rental_office_id = rental_office_id;
    }

    public Date getBilling_date() {
        return billing_date;
    }

    public void setBilling_date(Date billing_date) {
        this.billing_date = billing_date;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getKm_driven() {
        return km_driven;
    }

    public void setKm_driven(int km_driven) {
        this.km_driven = km_driven;
    }

    public int getFuel_tank() {
        return fuel_tank;
    }

    public void setFuel_tank(int fuel_tank) {
        this.fuel_tank = fuel_tank;
    }

    public int getDroppoint() {
        return droppoint;
    }

    public void setDroppoint(int droppoint) {
        this.droppoint = droppoint;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}