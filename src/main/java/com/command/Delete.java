package com.command;

import com.model.*;
import com.service.AutoService;
import com.service.BusService;
import com.service.MotorbikeService;
import com.util.UserInputUtil;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Delete implements Command {
    private static final BusService BUS_SERVICE = BusService.getInstance();
    private static final AutoService AUTO_SERVICE = AutoService.getInstance();
    private static final MotorbikeService MOTORBIKE_SERVICE = MotorbikeService.getInstance();
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void execute() {

        final VehicleType[] values = VehicleType.values();
        final List<String> names = getNames(values);
        final int userInput = UserInputUtil.getUserInput("What you want to delete:", names);
        final VehicleType value = values[userInput];

        switch (value) {
            case BUS -> BUS_SERVICE.delete((Bus) Objects.requireNonNull(deleteVehicle(BUS_SERVICE.getAll())));
            case AUTO -> AUTO_SERVICE.delete((Auto) Objects.requireNonNull(deleteVehicle(AUTO_SERVICE.getAll())));
            case MOTORBIKE ->
                    MOTORBIKE_SERVICE.delete((Motorbike) Objects.requireNonNull(deleteVehicle(MOTORBIKE_SERVICE.getAll())));
            default -> throw new IllegalArgumentException("Cannot delete " + value);
        }
    }


    public static int getUserInput(String line, List<String> list) {
        int userInput;
        do {
            System.out.println(line);
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s%n", i, list.get(i));
            }
            userInput = SCANNER.nextInt();
        } while (userInput < 0 || userInput >= list.size());
        return userInput;
    }

    public static String getUserInput(String line) {
        String userInput;
        do {
            System.out.println(line);
            userInput = SCANNER.next();
        } while (userInput == null);
        return userInput;
    }


    private static List<String> getNames(VehicleType[] values) {
        final List<String> names = new ArrayList<>(values.length);
        for (VehicleType type : values) {
            names.add(type.name());
        }
        return names;
    }

    private <T extends Vehicle> Constable deleteVehicle(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        int userInput;
        do {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d) %s%n", i, list.get(i));
            }
            userInput = SCANNER.nextInt();
        } while (userInput < 0 || userInput >= list.size());
        System.out.println(list.get(userInput).getId());
        return list.get(userInput).getId();
    }

}
