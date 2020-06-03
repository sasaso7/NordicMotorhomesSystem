package com.example.demo.Model;

import com.example.demo.Repository.CityRepo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;

public class Pricing {
    public static int extrasPrice(Extra extraObj, int daysBetween){

        //Her bestemmer man prisen pr dag i € for de forskellige extras.

        int picnic_table_price = 1;
        int bed_price = 2;
        int chair_price = 1;
        int bike_rack_price = 1;
        int child_seat_price = 0;
        int totalPrice = 0;

        //Her bliver totalprice sat til totalprice + alle extras antal * pris per dag * antal dage

        totalPrice += extraObj.getPicnic_tables() * picnic_table_price * daysBetween;
        totalPrice += extraObj.getBeds() * bed_price * daysBetween;
        totalPrice += extraObj.getChairs() * chair_price * daysBetween;
        totalPrice += extraObj.getBike_racks() * bike_rack_price * daysBetween;
        totalPrice += extraObj.getChild_seats() * child_seat_price * daysBetween;

        return totalPrice;

    }
    public static int totalPriceMinimum(int totalPrice){
        if(totalPrice > 200) {
            return totalPrice;
        } else {
            return 200;
        }

    }
    public static void createReceipt(Reservation reservation, Customer customer, Extra extra, EndedReservation endedReservation, City city, Motorhome motorhome, Address address, int daysBetween) throws FileNotFoundException {


        String name = "Receipt/" + reservation.getReservation_id() + customer.getFirst_name() + customer.getLast_name() + ".txt";
        File file = new File(name);
        PrintStream output = new PrintStream(new FileOutputStream(file, true));

        output.println("NORDIC MOTORHOME - ORDER NR: #" + reservation.getReservation_id());
        output.println("Billing date: " + endedReservation.getBilling_date());
        output.println("Reservation start date: " + reservation.getStart_date() + " - end date: " + reservation.getEnd_date() + " - Rental days: " + daysBetween);
        output.println("Customer first name: " + customer.getFirst_name() + " - Last name: " + customer.getLast_name());
        output.println("Zip: " + city.getZip_code() + " - City: " + city.getCity());
        output.println("Street: " + address.getAddress_1());
        output.println();

        output.println("----------------------------------------------------------------------");
        output.println("You have driven the: " + motorhome.getModel() + " from " + motorhome.getBrand());
        if(getSeasonFee(reservation) == 1.3){
            output.println("The price per day is: " + (motorhome.getPrice_per_day() * 1.3) + "€");
            output.println("Total price for camper: " + (motorhome.getPrice_per_day() * 1.3 * daysBetween) + "€");
        } else if(getSeasonFee(reservation) == 1.6) {
            output.println("The price per day is: " + (motorhome.getPrice_per_day() * 1.6) + "€");
            output.println("Total price for camper: " + (motorhome.getPrice_per_day() * 1.6 * daysBetween) + "€");
        } else {
            output.println("The price per day is: " + motorhome.getPrice_per_day() + "€");
            output.println("Total price for camper: " + (motorhome.getPrice_per_day() * daysBetween) + "€");
        }
        output.println("----------------------------------------------------------------------");
        if(endedReservation.getKm_driven() > daysBetween * 400){
            output.println("You have driven a total of: " + endedReservation.getKm_driven() + "km - Your maximum 400km pr day is " + daysBetween * 400);
            output.println("1€ pr extra kilometer:     " + (endedReservation.getKm_driven() - daysBetween * 400) + "€");
        } else {
            output.println("You have driven a total of: " + endedReservation.getKm_driven() + " - Your maximum 400km pr day is " + daysBetween * 400);
        }

        output.println("----------------------------------------------------------------------");
        output.println("Extra picnic tables: " + extra.getPicnic_tables() + " - price per day: 1€");
        output.println("Extra beds         : " + extra.getBeds() + " - price per day: 2€");
        output.println("Extra chairs       : " + extra.getChairs() + " - price per day: 1€");
        output.println("Extra bike racks   : " + extra.getBike_racks() + " - price per day: 1€");
        output.println("Extra child seats  : " + extra.getChild_seats() + " - price per day: 0€");
        output.println("----------------------------------------------------------------------");
        output.println("Extras total: " + extrasPrice(extra, daysBetween) + "€");
        output.println("----------------------------------------------------------------------");
        output.println("Total price for repairs: " + endedReservation.getExtra_repair() +"€");
        output.println("----------------------------------------------------------------------");
        if(endedReservation.getFuel_tank() == 1){
            output.println("Total price for refuling: 70€");
            output.println("----------------------------------------------------------------------");
        }
        if(reservation.getDroppoint() > 0){
            output.println("The motor home was picked up " + reservation.getDroppoint() + " kilometers from the rental");
            output.println("Total price for pickup: " + reservation.getDroppoint() * 0.7 + "€ (0.7€/km)");
            output.println("----------------------------------------------------------------------");
        }
        if(endedReservation.getDroppoint() > 0){
            output.println("The motor home was left " + endedReservation.getDroppoint() + " kilometers from the rental");
            output.println("Total price for driving it back: " + endedReservation.getDroppoint() * 0.7 + "€ (0.7€/km)");
            output.println("----------------------------------------------------------------------");
        }
        output.println("----------------------------------------------------------------------");
        output.println("TOTAL PRICE FOR RENTAL: " + endedReservation.getTotal_price() + "€");
        output.println("----------------------------------------------------------------------");
        output.println("BILLING MAIL: " + customer.getEmail());


    }
    public static double getSeasonFee(Reservation reservation){
      //  0% 11 12 01 02
        //    30% 03 04 09 10
          //  60% 05 06 07 08
        //muligvis fejl, den siger feks Calendar.MARCH er = 2, og ikke 3. SER ud til det virker kun testet hurtigt

        if(reservation.getStart_date().getMonth() == Calendar.MARCH || reservation.getStart_date().getMonth() == Calendar.APRIL || reservation.getStart_date().getMonth() == Calendar.SEPTEMBER || reservation.getStart_date().getMonth() == Calendar.OCTOBER){
            return 1.3;
        }else if(reservation.getStart_date().getMonth() == Calendar.MAY || reservation.getStart_date().getMonth() == Calendar.JUNE || reservation.getStart_date().getMonth() == Calendar.JULY || reservation.getStart_date().getMonth() == Calendar.AUGUST){
            return 1.6;
        } else {
            return 1;
        }
    }
}
