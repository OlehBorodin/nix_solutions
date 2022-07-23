package com.service;

import com.model.Bus;
import com.model.ManufacturerBus;
import com.repository.CrudRepository;

import java.math.BigDecimal;

public class BusService extends VehicleService<Bus> {


    public BusService(CrudRepository<Bus> repository) {
        super(repository);
    }

    @Override
    protected Bus create() {
        return new Bus("Model Bus", 12, 99,
                ManufacturerBus.ANKAI, BigDecimal.valueOf(RANDOM.nextInt(1000)));
    }


    protected Bus cretaOne() {
        return new Bus(
                "New bus model",
                123,
                65,
                ManufacturerBus.ANKAI,
                BigDecimal.valueOf(12200)
        );
    }
    }