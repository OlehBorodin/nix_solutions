package com.service;

import com.model.ManufacturerMotorbike;
import com.model.Motorbike;
import com.repository.MotorbikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MotorbikeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoService.class);
    private static final Random RANDOM = new Random();
    private final MotorbikeRepository motorbikeRepository;

    public MotorbikeService(MotorbikeRepository motorbikeRepository) {
        this.motorbikeRepository = motorbikeRepository;
    }

    public List<Motorbike> createAndSaveMotorbike(int count) {
        List<Motorbike> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Motorbike motorbike = new Motorbike("Model-" + RANDOM.nextInt(100), BigDecimal.valueOf(RANDOM.nextDouble(1000.0)), getRandomManufacturer());
            result.add(motorbike);
            motorbikeRepository.save(motorbike);
            LOGGER.debug("Created motorbike {}", motorbike.getId());
        }
        return result;
    }

    private ManufacturerMotorbike getRandomManufacturer() {
        final ManufacturerMotorbike[] values = ManufacturerMotorbike.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }


    public void saveMotorbike(List<Motorbike> motorbikes) {
        motorbikeRepository.saveAll(motorbikes);
    }

    public void printAll() {
        for (Motorbike motorbike : motorbikeRepository.getAll()) {
            System.out.println(motorbike);
        }
    }

    public Motorbike findOneById(String id) {
        if (id == null) {
            return motorbikeRepository.getById("");
        } else {
            return motorbikeRepository.getById(id);
        }
    }

    public boolean update(Motorbike motorbike) {
        if(motorbikeRepository.getById(motorbike.getId())!=null){
            LOGGER.debug("Update motorbike {}", motorbike.getId());
        }
        return motorbikeRepository.update(motorbike);
    }

    public boolean delete(Motorbike motorbike) {
        if(motorbikeRepository.delete(motorbike.getId())){
            LOGGER.debug("Delete motorbike {}", motorbike.getId());
            return true;
        }
        return false;
    }
}
