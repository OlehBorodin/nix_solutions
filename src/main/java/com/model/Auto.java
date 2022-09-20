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
        super(model, price, count, VehicleType.AUTO);
        this.manufacturerAuto = manufacturerAuto;
        this.bodyType = bodyType;
    }

    public Auto() {
        super();
    }

    public static class Builder {
        private final Auto newAuto;

        public Builder() {
            newAuto = new Auto();
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "newAuto=" + newAuto +
                    '}';
        }

        public Builder setModel(String model) {
            newAuto.model = model;
            return this;
        }

        public Builder setAutoManufacturer(ManufacturerAuto manufacturerAuto) {
            newAuto.manufacturerAuto = manufacturerAuto;
            return this;
        }

        public Builder setBodyType(BodyType bodyType) {
            newAuto.bodyType = bodyType;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            newAuto.price = price;
            return this;
        }

        public Builder setCount(int count) {
            newAuto.count = count;
            return this;
        }

        public Auto build() {
            if (newAuto.price == null) {
                throw new IllegalArgumentException("Price is null");
            } else if (newAuto.count <= 0) {
                throw new IllegalArgumentException("Count <= 0>");
            } else if (BodyType.values().length > 20) {
                throw new IllegalArgumentException("BodyType length > 20");
            } else if (newAuto.id == null) {
                throw new IllegalArgumentException("Id is null");
            }
            return newAuto;
        }

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
                ", type=" + type +
                '}';
    }
}
