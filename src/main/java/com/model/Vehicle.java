package com.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public abstract class Vehicle {
    protected  String id;
    protected String model;
    protected String restyling;
    protected BigDecimal price;
    protected DateTimeFormatter time;
    protected int count;
    protected VehicleType type;

    protected Vehicle(String model, BigDecimal price, int count, VehicleType type) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.count = count;
        this.restyling = UUID.randomUUID().toString();
        this.time =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.type = type;
    }

    public Vehicle() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

