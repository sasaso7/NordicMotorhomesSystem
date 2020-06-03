package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Motorhome {
    @Id
    private int motorHome_id;
    @Size(min= 3, max = 14, message = "Brands can only be between 3 and 14 characters")
    private String brand;
    private String model;
    @Positive(message = "Odometer can only be positive")
    private int odometer;
    @Min(value = 2, message = "Motorhomes have to have more than 2 seats!")
    private int seats;
    @Min(value = 1, message = "There are no motorhomes with 0 beds")
    private int beds;
    private String fuel;
    private int price_per_day;
    private int size_id;

    public Motorhome() {

    }

    public Motorhome(int motorHome_id, String brand, String model, int odometer, int seats, int beds, String fuel, int price_per_day, int size_id) {
        this.motorHome_id = motorHome_id;
        this.brand = brand;
        this.model = model;
        this.odometer = odometer;
        this.seats = seats;
        this.beds = beds;
        this.fuel = fuel;
        this.price_per_day = price_per_day;
        this.size_id = size_id;
    }

    public int getMotorHome_id() {
        return motorHome_id;
    }

    public void setMotorHome_id(int motorHome_id) {
        this.motorHome_id = motorHome_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(int price_per_day) {
        this.price_per_day = price_per_day;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

}