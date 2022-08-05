package com.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
public abstract class Vehicle {
    protected final String id;
    protected String model;
    protected String restyling;
    protected BigDecimal price;
    protected DateTimeFormatter time;
    protected int count;

    protected Vehicle(String model, BigDecimal price, int count) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.count = count;
        this.restyling = UUID.randomUUID().toString();
        this.time =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");;
    }

}
