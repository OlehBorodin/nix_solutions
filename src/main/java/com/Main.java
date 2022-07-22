package com;

import com.model.*;
import com.repository.AutoRepository;
import com.repository.BusRepository;
import com.repository.MotorbikeRepository;
import com.service.AutoService;
import com.service.BusService;
import com.service.MotorbikeService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final AutoService AUTO_SERVICE = new AutoService(new AutoRepository());
    private static final BusService BUS_SERVICE = new BusService(new BusRepository());
    private static final MotorbikeService MOTORBIKE_SERVICE = new MotorbikeService(new MotorbikeRepository());

    public static void main(String[] args) {
        System.out.println("Hello,\n" +
                " if you want create autos touch  1\n" +
                " if you want create buses touch 2\n" +
                " if you want create motorbike touch 3");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        switch (choice) {
            case 1: {
                System.out.println("You create auto");
                final List<Auto> autos = AUTO_SERVICE.createAndSaveAutos(4);
                AUTO_SERVICE.saveAutos(autos);
                AUTO_SERVICE.printAll();
                AUTO_SERVICE.optionalExmaples();

                Auto auto = autos.get(2);
                auto.setModel("new model");
                auto.setPrice(BigDecimal.TEN);
                auto.setManufacturerAuto(ManufacturerAuto.ZAZ);
                auto.setBodyType(BodyType.CONVERTIBLE);
                System.out.println("___________________________________________");
                AUTO_SERVICE.update(auto);
                //AUTO_SERVICE.delete(autos.get(0)
                // );
                AUTO_SERVICE.printAll();
                AUTO_SERVICE.optionalExmaples();
                break;
            }
            case 2: {
                System.out.println("You create bus");
                final List<Bus> buses = BUS_SERVICE.createAndSaveBuses(5);
                BUS_SERVICE.saveBuses(buses);
                BUS_SERVICE.printAll();
                BUS_SERVICE.optionalExmaples();

                Bus bus = buses.get(0);
                bus.setBusRoute(12);
                bus.setPassengersNumber(29);
                bus.setManufacturerBus(ManufacturerBus.FOTON);
                bus.setPrice(BigDecimal.ONE);
                bus.setModel("new model");
                System.out.println("___________________________________________");
                BUS_SERVICE.saveBuses(buses);
                BUS_SERVICE.printAll();
                BUS_SERVICE.optionalExmaples();
                break;
            }
            case 3:{
                System.out.println("You create motorbike");
                final List<Motorbike> motorbikes = MOTORBIKE_SERVICE.createAndSaveMotorbike(6);
                MOTORBIKE_SERVICE.saveMotorbike(motorbikes);
                MOTORBIKE_SERVICE.printAll();
                MOTORBIKE_SERVICE.optionalExmaples();

                Motorbike motorbike = motorbikes.get(4);
                motorbike.setManufacturerMotorbike(ManufacturerMotorbike.BMW);
                motorbike.setModel("new model");
                motorbike.setPrice(BigDecimal.valueOf(1234));
                System.out.println("___________________________________________");
                MOTORBIKE_SERVICE.saveMotorbike(motorbikes);
                MOTORBIKE_SERVICE.printAll();
                MOTORBIKE_SERVICE.optionalExmaples();
                break;
            }
            default:{
                System.out.println("Sorry, you wrote incorrect number");
                break;
            }

        }
    }




    }


