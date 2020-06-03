package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Size {
    @Id
    private int size_id;
    private int length;
    private int height;
    private int weight;

    public Size(){}

    public Size(int size_id, int length, int height, int weight){
        this.size_id = size_id;
        this.length = length;
        this.height = height;
        this.weight = weight;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}
