package com.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Auto extends Vehicle {
    private BodyType bodyType;
    private ManufacturerAuto manufacturerAuto;

    public Auto(String model, BigDecimal price, ManufacturerAuto manufacturerAuto, BodyType bodyType) {
        super(model, price);
        this.manufacturerAuto = manufacturerAuto;
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "bodyType='" + bodyType + '\'' +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturerAuto +
                '}';
    }
}
