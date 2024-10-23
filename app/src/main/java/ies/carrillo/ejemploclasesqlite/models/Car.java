package ies.carrillo.ejemploclasesqlite.models;

import java.io.Serializable;

public class Car implements Serializable {
    private long id;
    private String brand;
    private String model;
    private String color;

    //Constructor without params
    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car with brand: " + brand + " and model: " + model + " and color: " + color + "";
    }

}
