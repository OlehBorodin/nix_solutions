package com.service;

import com.model.Auto;
import com.model.BodyType;
import com.model.ManufacturerAuto;
import com.repository.CrudRepository;

import java.math.BigDecimal;

public class AutoService extends VehicleService <Auto> {



    public AutoService(CrudRepository<Auto> repository) {
        super(repository);
    }


    private ManufacturerAuto getRandomManufacturer() {
        final ManufacturerAuto[] values = ManufacturerAuto.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }


    @Override
    protected Auto create() {
        return new Auto("M5", BigDecimal.valueOf(RANDOM.nextInt(1000)),
                ManufacturerAuto.BMW,BodyType.SEDAN);
    }

    protected Auto cretaOne() {
        return new Auto(
                "Model new",
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                getRandomManufacturer(),
                BodyType.CONVERTIBLE
        );
    }

}



