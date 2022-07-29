package com;

import com.garage.Garage;
import com.model.*;
import com.repository.AutoRepository;
import com.repository.BusRepository;
import com.repository.MotorbikeRepository;
import com.sell.Discount;
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

        Garage garage = new Garage();
        System.out.println("Hello,\n" +
                " if you want create autos touch  1\n" +
                " if you want create buses touch 2\n" +
                " if you want create motorbike touch 3");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.println("You create auto");
                final List<Auto> autos = AUTO_SERVICE.createAndSaveVehicle(4);
                AUTO_SERVICE.saveVehicle(autos);
                AUTO_SERVICE.printAll();
                AUTO_SERVICE.optionalExmaples();


                Auto auto = autos.get(2);
                auto.setModel("new model");
                auto.setPrice(BigDecimal.TEN);
                auto.setManufacturerAuto(ManufacturerAuto.ZAZ);
                auto.setBodyType(BodyType.CONVERTIBLE);
                System.out.println("___________________________________________");
                AUTO_SERVICE.update(auto);
                AUTO_SERVICE.delete(autos.get(0));
                AUTO_SERVICE.printAll();
                AUTO_SERVICE.optionalExmaples();
                System.out.println("___________________________________________");
                Discount<Auto> autoDiscount = new Discount<>(auto);
                autoDiscount.printVehicle(auto);
                autoDiscount.getDiscount(auto);
                autoDiscount.updatePrice( 20);
                System.out.println("___________________________________________");
                garage.add(autos.get(1));
                garage.add(auto);
                garage.addHead(autos.get(2));
                garage.printAll();
                System.out.println(garage.getRestylingVehicle(auto.getRestyling()));
                garage.forEach();
                System.out.println("------forEach---------");
                garage.deleteRestylingVehicle(auto.getRestyling());
                System.out.println("_____delete_____");
                garage.printAll();
                System.out.println(garage.restylingNumber(auto));
                System.out.println("--------");
                garage.printAll();
                System.out.println(garage.getRestylingData());

            }
            case 2 -> {
                System.out.println("You create bus");
                final List<Bus> buses = BUS_SERVICE.createAndSaveVehicle(5);
                BUS_SERVICE.saveVehicle(buses);
                BUS_SERVICE.printAll();
                BUS_SERVICE.optionalExmaples();

                Bus bus = buses.get(0);
                bus.setBusRoute(12);
                bus.setPassengersNumber(29);
                bus.setManufacturerBus(ManufacturerBus.FOTON);
                bus.setPrice(BigDecimal.ONE);
                bus.setModel("new model");
                System.out.println("___________________________________________");
                BUS_SERVICE.saveVehicle(buses);
                BUS_SERVICE.printAll();
                BUS_SERVICE.optionalExmaples();
                System.out.println("___________________________________________");
                Discount<Bus> busDiscount = new Discount<>(bus);
                busDiscount.printVehicle(bus);
                busDiscount.getDiscount(bus);
                busDiscount.updatePrice( 30);
                System.out.println("___________________________________________");
                garage.add(buses.get(1));
                garage.add(bus);
                garage.addHead(buses.get(2));
                garage.printAll();
                System.out.println(garage.getRestylingVehicle(bus.getRestyling()));
                garage.forEach();
                System.out.println("------forEach---------");
                garage.deleteRestylingVehicle(bus.getRestyling());
                System.out.println("_____delete_____");
                garage.printAll();
                System.out.println(garage.restylingNumber(bus));
                System.out.println("--------");
                garage.printAll();
                System.out.println(garage.getRestylingData());
            }
            case 3 -> {
                System.out.println("You create motorbike");
                final List<Motorbike> motorbikes = MOTORBIKE_SERVICE.createAndSaveVehicle(6);
                MOTORBIKE_SERVICE.saveVehicle(motorbikes);
                MOTORBIKE_SERVICE.printAll();
                MOTORBIKE_SERVICE.optionalExmaples();

                Motorbike motorbike = motorbikes.get(4);
                motorbike.setManufacturerMotorbike(ManufacturerMotorbike.BMW);
                motorbike.setModel("new model");
                motorbike.setPrice(BigDecimal.valueOf(1234));
                System.out.println("___________________________________________");
                MOTORBIKE_SERVICE.saveVehicle(motorbikes);
                MOTORBIKE_SERVICE.printAll();
                MOTORBIKE_SERVICE.optionalExmaples();
                System.out.println("___________________________________________");
                Discount<Motorbike> motorbikeDiscount = new Discount<>(motorbike);
                motorbikeDiscount.printVehicle(motorbike);
                motorbikeDiscount.getDiscount(motorbike);
                motorbikeDiscount.updatePrice( 50);
                System.out.println("___________________________________________");
                garage.add(motorbikes.get(1));
                garage.add(motorbike);
                garage.addHead(motorbikes.get(2));
                garage.printAll();
                System.out.println("--------------");
                System.out.println(garage.getRestylingVehicle(motorbike.getRestyling()));
                garage.forEach();
                System.out.println("------forEach---------");
                garage.deleteRestylingVehicle(motorbike.getRestyling());
                System.out.println("_____delete_____");
                garage.printAll();
                System.out.println(garage.restylingNumber(motorbike));
                System.out.println("--------");
                garage.printAll();
                System.out.println(garage.getRestylingData());
            }
            default -> {
                System.out.println("Sorry, you wrote incorrect number");
            }
        }
    }


    }


