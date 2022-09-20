package com.command;

import com.model.Auto;
import com.model.BodyType;
import com.model.ManufacturerAuto;

import java.math.BigDecimal;

public class Builder implements Command {
    @Override
    public void execute() {
        Auto auto = new Auto.Builder()
                .withAutoManufacturer(ManufacturerAuto.KIA)
                .withCount(1)
                .withPrice(BigDecimal.valueOf(7000000))
                .withBodyType(BodyType.COUPE)
                .withModel("NM-3")
                .build();
        System.out.println(auto);
    }
}
