package com.command;

import com.model.Auto;
import com.model.BodyType;
import com.model.ManufacturerAuto;

import java.math.BigDecimal;

public class Builder implements Command {
    @Override
    public void execute() {
        Auto auto = new Auto.Builder()
                .setAutoManufacturer(ManufacturerAuto.KIA)
                .setCount(1)
                .setPrice(BigDecimal.valueOf(7000000))
                .setBodyType(BodyType.COUPE)
                .setModel("NM-3")
                .build();
        System.out.println(auto);
    }
}
