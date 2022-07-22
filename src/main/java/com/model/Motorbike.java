package com.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class Motorbike extends Vehicle {

    protected ManufacturerMotorbike manufacturerMotorbike;

    public Motorbike(String model, BigDecimal price, ManufacturerMotorbike manufacturerMotorbike) {
        super(model, price);
        this.manufacturerMotorbike = manufacturerMotorbike;
    }

    @Override
    public String toString() {
        return "Motorbike{" +
                "manufacturerMotorbike=" + manufacturerMotorbike +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}