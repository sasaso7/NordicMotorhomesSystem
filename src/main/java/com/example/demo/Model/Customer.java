package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
@Entity
    public class Customer extends Person {
        @Id
        private int customer_id;
        private Date create_date;
        private int address_id;

        public Customer(){

        }
        public Customer(int customer_id, String first_name, String last_name, String email, Date create_date, int address_id) {
            super(first_name, last_name, email);
            this.customer_id = customer_id;
            this.create_date = new Date(System.currentTimeMillis());
            this.address_id = address_id;
        }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getCreate_date() {
            return create_date;
        }

        public void setCreate_date(Date create_date) {
            this.create_date = create_date;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }


    }

