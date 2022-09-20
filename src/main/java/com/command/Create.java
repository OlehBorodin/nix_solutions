package com.command;

import com.model.VehicleType;
import com.service.AutoService;
import com.service.BusService;
import com.service.MotorbikeService;
import com.util.UserInputUtil;

import java.util.ArrayList;
import java.util.List;

public class Create implements Command {

    private static final BusService BUS_SERVICE = BusService.getInstance();
    private static final AutoService AUTO_SERVICE = AutoService.getInstance();
    private static final MotorbikeService MOTORBIKE_SERVICE = MotorbikeService.getInstance();
    @Override
    public void execute() {

        final VehicleType[] values = VehicleType.values();
        final List<String> names = getNames(values);
        final int userInput = UserInputUtil.getUserInput("What you want to create:", names);
        final VehicleType value = values[userInput];

        switch (value) {
            case BUS -> BUS_SERVICE.createAndSaveVehicle(1);
            case AUTO -> AUTO_SERVICE.createAndSaveVehicle(1);
            case MOTORBIKE -> MOTORBIKE_SERVICE.createAndSaveVehicle(1);
            default -> throw new IllegalArgumentException("Cannot build " + value);
        }
    }

    private static List<String> getNames(VehicleType[] values) {
        final List<String> names = new ArrayList<>(values.length);
        for (VehicleType type : values) {
            names.add(type.name());
        }
        return names;
    }
    }

