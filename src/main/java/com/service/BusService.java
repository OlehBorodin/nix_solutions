package com.service;

import com.model.Bus;
import com.model.ManufacturerBus;
import com.model.Vehicle;
import com.repository.BusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoService.class);
    private static final Random RANDOM = new Random();
    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> createAndSaveBuses(int count) {
        List<Bus> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Bus bus = new Bus("Model-" + RANDOM.nextInt(100),
                    RANDOM.nextInt(100), RANDOM.nextInt(100), getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)));
            result.add(bus);
            busRepository.save(bus);
            LOGGER.debug("Created bus {}", bus.getId());
        }
        return result;
    }

    private ManufacturerBus getRandomManufacturer() {
        final ManufacturerBus[] values = ManufacturerBus.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public boolean saveBuses(List<Bus> buses) {

        busRepository.saveAll(buses);
        return false;
    }

    public void printAll() {
        for (Bus bus : busRepository.getAll()) {
            System.out.println(bus);
        }
    }


    public Optional<Bus> findOneById(String id) {
        return id == null ? busRepository.findById("") : busRepository.findById(id);
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
        final Bus bus = createAndSaveBuses(1).get(0);
        final String id = bus.getId();

        ifPresent(id);
        orElse(id);
        orElseGet(id);
        map(id);
    }

    private void ifPresent(String id) {
        busRepository.findById(id).ifPresent(auto -> {
            System.out.println(auto.getModel());
        });

        busRepository.findById("123").ifPresent(auto -> {
            System.out.println(auto.getModel());
        });
    }
    private void orElse(String id) {
        final Bus bus1 = busRepository.findById(id).orElse(createNewBus());
        System.out.println(bus1.getModel());

        System.out.println("~".repeat(10));

        final Bus bus2 = busRepository.findById("432").orElse(createNewBus());
        System.out.println(bus2.getModel());
    }


    private void orElseGet(String id) {
        final Bus bus = busRepository.findById(id).orElse(createNewBus());
        final Bus bus1 = busRepository.findById(id).orElseGet(this::createNewBus);
        System.out.println(bus1.getModel());
        System.out.println("~".repeat(10));

        final Bus bus2 = busRepository.findById("123").orElseGet(() -> {
            System.out.println("Cannot find bus with id " + "123");
            return createNewBus();
        });
        System.out.println(bus2.getModel());
    }


    private void map(String id) {
        busRepository.findById(id)
                .map(Vehicle::getModel)
                .ifPresent(System.out::println);
    }


    public boolean update(Bus bus) {
        if(busRepository.findById(bus.getId()).isPresent()){
            LOGGER.debug("Update bus {}", bus.getId());
        }
        return busRepository.update(bus);
    }

    public boolean delete(Bus bus) {
        if(busRepository.delete(bus.getId())){
            LOGGER.debug("Delete bus {}", bus.getId());
            return true;
        }
        return false;
    }
}
