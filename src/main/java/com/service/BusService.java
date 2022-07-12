package com.service;

import com.model.Bus;
import com.model.ManufacturerBus;
import com.repository.BusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
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
            final Bus bus = new Bus("Model-" + RANDOM.nextInt(100), RANDOM.nextInt(100), RANDOM.nextInt(100), getRandomManufacturer(), BigDecimal.valueOf(RANDOM.nextDouble(1000.0)));
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

    public void saveBuses(List<Bus> buses) {
        busRepository.saveAll(buses);
    }

    public void printAll() {
        for (Bus bus : busRepository.getAll()) {
            System.out.println(bus);
        }
    }

    public Bus findOneById(String id) {
        if (id == null) {
            return busRepository.getById("");
        } else {
            return busRepository.getById(id);
        }
    }

    public boolean update(Bus bus) {
        if(busRepository.getById(bus.getId())!=null){
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
