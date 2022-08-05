package com.sell;

import com.model.Vehicle;

import java.math.BigDecimal;

public class Discount<T extends Vehicle> {
    private final T vehicle;

    public Discount(T vehicle) {
        this.vehicle = vehicle;
    }

    public void printVehicle(T vehicle) {
        System.out.println(vehicle);
    }

    public BigDecimal getDiscount(T vehicle) {
        BigDecimal price = vehicle.getPrice();
        double random = Math.random();
        double range = (30 - 10) * random + 10;
        return price.subtract(price
                .multiply(BigDecimal.valueOf(range)
                        .divide(BigDecimal.valueOf(100))));
    }


    public <O extends Number> BigDecimal updatePrice(Number X) {
        vehicle.setPrice(BigDecimal.valueOf(vehicle.getPrice().doubleValue() + X.doubleValue()));
        System.out.println(vehicle);
        return vehicle.getPrice();
    }
}