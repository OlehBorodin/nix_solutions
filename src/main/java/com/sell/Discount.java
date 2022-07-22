package com.sell;

import com.model.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public class Discount<T extends Vehicle> {
    private final T vehicle;

    public Discount(T vehicle) {
        this.vehicle = vehicle;
    }

    public void printAll(List<T> vehicles) {
        for (T vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    public void printVehicle(T vehicle) {
        System.out.println(vehicle);
    }

    public BigDecimal getDiscount(T vehicle) {
        double random = Math.random();
        double range = (30 - 10) * random + 10;
        return BigDecimal.valueOf(range);
    }


    public void updatePrice(Number X) {
        vehicle.setPrice(BigDecimal.valueOf(vehicle.getPrice().doubleValue() + X.doubleValue()));
    }
}
