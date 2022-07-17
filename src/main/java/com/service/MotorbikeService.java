package com.service;

import com.model.ManufacturerMotorbike;
import com.model.Motorbike;
import com.model.Vehicle;
import com.repository.MotorbikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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


    public boolean saveMotorbike(List<Motorbike> motorbikes) {
        motorbikeRepository.saveAll(motorbikes);
        return false;
    }

    public void printAll() {
        for (Motorbike motorbike : motorbikeRepository.getAll()) {
            System.out.println(motorbike);
        }
    }

    public Optional<Motorbike> findOneById(String id) {
        return id == null ? motorbikeRepository.findById("") : motorbikeRepository.findById(id);
    }

    private Motorbike createMotorbike() {
        return new Motorbike(
                "Model new Motorbike",
                BigDecimal.valueOf(10000),
                ManufacturerMotorbike.KAWASAKI
        );
    }
    public void optionalExmaples() {
        final Motorbike motorbike = createAndSaveMotorbike(1).get(0);
        final String id = motorbike.getId();

        orElseThrow(id);
        or(id);
        filter(id);
        map(id);
        ifPresentOrElse(id);
    }

    private void orElseThrow(String id) {
        final Motorbike motorbike1 = motorbikeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find motorbike with id " + id));
        System.out.println(motorbike1.getModel());

        System.out.println("~".repeat(10));

        try {
            final Motorbike motorbike2 = motorbikeRepository.findById("123")
                    .orElseThrow(() -> new IllegalArgumentException("Cannot find motorbike with id " + "123"));
            System.out.println(motorbike2.getModel());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void or(String id) {
        final Optional<Motorbike> motorbike1 = motorbikeRepository.findById(id).or(() -> Optional.of(createMotorbike()));
        motorbike1.ifPresent(motorbike -> {
            System.out.println(motorbike.getModel());
        });
        System.out.println("~".repeat(10));

        final Optional<Motorbike> motorbike2 = motorbikeRepository.findById("123").or(() -> Optional.of(createMotorbike()));
        motorbike2.ifPresent(motorbike -> {
            System.out.println(motorbike.getModel());
        });
    }

    private void filter(String id) {
        motorbikeRepository.findById(id)
                .filter(motorbike -> !motorbike.getManufacturerMotorbike().equals(""))
                .ifPresent(motorbike -> {
                    System.out.println(motorbike.getModel());
                });

        motorbikeRepository.findById(id)
                .filter(motorbike -> motorbike.getManufacturerMotorbike().equals(""))
                .ifPresent(motorbike -> {
                    System.out.println(motorbike.getModel());
                });
    }

    private void map(String id) {
        motorbikeRepository.findById(id)
                .map(Vehicle::getModel)
                .ifPresent(System.out::println);
    }

    private void ifPresentOrElse(String id) {
        motorbikeRepository.findById(id).ifPresentOrElse(
                motorbike -> {
                    System.out.println(motorbike.getModel());
                },
                () -> {
                    System.out.println("Cannot find motorbike with id " + "123");
                }
        );

        motorbikeRepository.findById("123").ifPresentOrElse(
                motorbike -> {
                    System.out.println(motorbike.getModel());
                },
                () -> {
                    System.out.println("Cannot find motorbike with id " + "123");
                }
        );
    }

    public boolean update(Motorbike motorbike) {
        if(motorbikeRepository.findById(motorbike.getId())!=null){
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
