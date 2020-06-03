package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

public class AfterReservation {
    @Id
    private int endedReservation_id;
    private int amount;
    private Date payment_date;
    private int completed;
    private int reservation_id;
    private int rental_office_id;
    private int droppoint;


    public AfterReservation() {
    }

    public AfterReservation(int endedReservation_id, int amount, Date payment_date, int completed, int reservation_id, int rental_office_id, int droppoint) {
        this.endedReservation_id = endedReservation_id;
        this.amount = amount;
        this.payment_date = payment_date;
        this.completed = completed;
        this.reservation_id = reservation_id;
        this.rental_office_id = rental_office_id;
        this.droppoint = droppoint;

    }

    public int getEndedReservation_id() {
        return endedReservation_id;
    }

    public void setEndedReservation_id(int endedReservation_id) {
        this.endedReservation_id = endedReservation_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
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

    public int getDroppoint() {
        return droppoint;
    }

    public void setDroppoint(int droppoint) {
        this.droppoint = droppoint;
    }
}