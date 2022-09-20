package com.service;

import com.model.*;

import java.math.BigDecimal;
import java.util.Random;

public  class VehicleFactory {
    private static VehicleFactory instance;

    private static final Random RANDOM = new Random();

    private VehicleFactory() {
    }

    private static VehicleFactory getInstance() {
        if (instance == null) {
            instance = new VehicleFactory();
        }
        return instance;
    }

    public Vehicle build(VehicleType type) {
        return switch (type) {
            case BUS -> new Bus(
                    "Model of bus",
                    701,
                    55,
                    ManufacturerBus.ANKAI,
                    BigDecimal.valueOf(123),
                    1

            );
            case AUTO -> new Auto(
                    "Auto model",
                    BigDecimal.valueOf(10000),
                    ManufacturerAuto.KIA,
                    BodyType.SEDAN,
                    1

            );
            case MOTORBIKE -> new Motorbike(
                    "Motorbike model",
                    BigDecimal.valueOf(10000),
                    ManufacturerMotorbike.HONDA,
                    1

            );
            default -> throw new IllegalArgumentException("Cannot build " + type);
        };
    }

}

