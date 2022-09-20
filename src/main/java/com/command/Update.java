package com.command;


import com.model.VehicleType;
import com.service.AutoService;
import com.service.BusService;
import com.service.MotorbikeService;
import com.util.UserInputUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Update implements Command {
    private static final BusService BUS_SERVICE = BusService.getInstance();
    private static final AutoService AUTO_SERVICE = AutoService.getInstance();
    private static final MotorbikeService MOTORBIKE_SERVICE = MotorbikeService.getInstance();
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void execute() {
        final VehicleType[] values = VehicleType.values();
        final List<String> names = getNames(values);
        final int userInput = UserInputUtil.getUserInput("What you want to update:", names);
        final VehicleType value = values[userInput];

        switch (value) {
            case BUS -> {
                System.out.println("Please enter bus id");
                String idBus = SCANNER.nextLine();
                System.out.println("Please enter price: ");
                BigDecimal priceBus = new BigDecimal(SCANNER.nextLine());
                BUS_SERVICE.updateVehicleByPrice(idBus, priceBus);
            }
            case AUTO -> {
                System.out.println("Please enter auto id");
                String idAuto = SCANNER.nextLine();
                System.out.println("Please enter price: ");
                BigDecimal priceAuto = new BigDecimal(SCANNER.nextLine());
                AUTO_SERVICE.updateVehicleByPrice(idAuto, priceAuto);
            }
            case MOTORBIKE -> {
                System.out.println("Please enter motorbike id");
                String idMotorbike = SCANNER.nextLine();
                System.out.println("Please enter price: ");
                BigDecimal priceMotorbike = new BigDecimal(SCANNER.nextLine());
                MOTORBIKE_SERVICE.updateVehicleByPrice(idMotorbike, priceMotorbike);
            }

            default -> throw new IllegalArgumentException("Cannot update " + value);
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
