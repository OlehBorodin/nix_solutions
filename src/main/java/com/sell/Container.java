package com.sell;

import com.model.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Container {
    private final List<Vehicle> vehicles;
    public Container() {
        vehicles = new ArrayList<>();
    }
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    public BigDecimal getDiscount(Vehicle vehicle) {
        double random = Math.random();
        double range = (30 - 10) * random + 10;
        return BigDecimal.valueOf(range);
    }
    public void getVehicles() {
        vehicles.forEach(System.out::println);
    }
    public void updatePrice(Vehicle vehicle, Number X) {
        vehicle.setPrice(BigDecimal.valueOf(vehicle.getPrice().doubleValue() + X.doubleValue()));
    }
}

