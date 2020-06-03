/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
@Entity
public class Reservation {
    @Id
    private int reservation_id;
    private Date start_date;
    private Date end_date;
    private Date creation_date;
    private int extra_id;
    private int employee_id;
    private int customer_id;
    private int motorHome_id;
    private int rental_office_id;
    private int droppoint;



    public Reservation() {
    }

    public Reservation(int reservation_id, Date start_date, Date end_date, Date creation_date, int extra_id, int employee_id, int customer_id, int motorHome_id, int rental_office_id, int droppoint) {
        this.reservation_id = reservation_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.creation_date = creation_date;
        this.extra_id = extra_id;
        this.employee_id = employee_id;
        this.customer_id = customer_id;
        this.motorHome_id = motorHome_id;
        this.rental_office_id = rental_office_id;
        this.droppoint = droppoint;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public int getExtra_id() {
        return extra_id;
    }

    public void setExtra_id(int extra_id) {
        this.extra_id = extra_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getMotorHome_id() {
        return motorHome_id;
    }

    public void setMotorHome_id(int motorHome_id) {
        this.motorHome_id = motorHome_id;
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
