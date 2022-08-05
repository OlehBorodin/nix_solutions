package com.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Auto extends Vehicle {
    private BodyType bodyType;
    private ManufacturerAuto manufacturerAuto;

    public Auto(String model, BigDecimal price, ManufacturerAuto manufacturerAuto, BodyType bodyType, int count) {
        super(model, price, count);
        this.manufacturerAuto = manufacturerAuto;
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "bodyType=" + bodyType +
                ", manufacturerAuto=" + manufacturerAuto +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", restyling='" + restyling + '\'' +
                ", price=" + price +
                ", time=" + time +
                ", count=" + count +
                '}';



    }
}
