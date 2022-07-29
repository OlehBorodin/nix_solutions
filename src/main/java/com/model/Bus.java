package com.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Bus extends Vehicle {

    protected int busRoute;
    protected int passengersNumber;

    protected ManufacturerBus manufacturerBus;


    public Bus(String model, int busRoute, int passengersNumber,
               ManufacturerBus manufacturerBus, BigDecimal price, int count) {
        super(model, price, count);
        this.busRoute = busRoute;
        this.manufacturerBus = manufacturerBus;
        this.passengersNumber = passengersNumber;


    }
    @Override
    public String toString() {
        return "Bus{" +
                "busRoute=" + busRoute +
                ", passengersNumber=" + passengersNumber +
                ", manufacturerBus=" + manufacturerBus +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", restyling='" + restyling + '\'' +
                ", price=" + price +
                ", time=" + time +
                ", count=" + count +
                '}';
    }
}
