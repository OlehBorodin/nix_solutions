package com.service;

import com.model.Bus;
import com.model.ManufacturerBus;
import com.model.Vehicle;
import com.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class BusService extends VehicleService<Bus> {


    public BusService(CrudRepository<Bus> repository) {
        super(repository);
    }

    @Override
    protected Bus create() {
        return new Bus("Model Bus", 12, 99,
                ManufacturerBus.ANKAI, BigDecimal.valueOf(RANDOM.nextInt(1000)));
    }


    private ManufacturerBus getRandomManufacturer() {
        final ManufacturerBus[] values = ManufacturerBus.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
    public Optional<Bus> findOneById(String id) {
        return id == null ? repository.findById("") : repository.findById(id);
    }

    private Bus createNewBus() {
        return new Bus(
                "New bus model",
                123,
                65,
                ManufacturerBus.ANKAI,
                BigDecimal.valueOf(12200)
        );
    }
    public void optionalExmaples() {
        final Bus bus = createAndSaveVehicle(1).get(0);
        final String id = bus.getId();

        ifPresent(id);
        orElse(id);
        orElseGet(id);
        map(id);
    }

    private void ifPresent(String id) {
        repository.findById(id).ifPresent(auto -> {
            System.out.println(auto.getModel());
        });

        repository.findById("123").ifPresent(auto -> {
            System.out.println(auto.getModel());
        });
    }
    private void orElse(String id) {
        final Bus bus1 = repository.findById(id).orElse(createNewBus());
        System.out.println(bus1.getModel());

        System.out.println("~".repeat(10));

        final Bus bus2 = repository.findById("432").orElse(createNewBus());
        System.out.println(bus2.getModel());
    }


    private void orElseGet(String id) {
        final Bus bus = repository.findById(id).orElse(createNewBus());
        final Bus bus1 = repository.findById(id).orElseGet(this::createNewBus);
        System.out.println(bus1.getModel());
        System.out.println("~".repeat(10));

        final Bus bus2 = repository.findById("123").orElseGet(() -> {
            System.out.println("Cannot find bus with id " + "123");
            return createNewBus();
        });
        System.out.println(bus2.getModel());
    }


    private void map(String id) {
        repository.findById(id)
                .map(Vehicle::getModel)
                .ifPresent(System.out::println);
    }

}