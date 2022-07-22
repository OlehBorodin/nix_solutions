package com.service;

import com.model.ManufacturerMotorbike;
import com.model.Motorbike;
import com.model.Vehicle;
import com.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class MotorbikeService extends VehicleService<Motorbike> {


    public MotorbikeService(CrudRepository<Motorbike> repository) {
        super(repository);
    }

    @Override
    protected Motorbike create() {
        return new Motorbike("Motorbike model", BigDecimal.valueOf(2000), ManufacturerMotorbike.HONDA);
    }

    private ManufacturerMotorbike getRandomManufacturer() {
        final ManufacturerMotorbike[] values = ManufacturerMotorbike.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
    public Optional<Motorbike> findOneById(String id) {
        return id == null ? repository.findById("") : repository.findById(id);
    }

    private Motorbike createMotorbike() {
        return new Motorbike(
                "Model new Motorbike",
                BigDecimal.valueOf(10000),
                ManufacturerMotorbike.KAWASAKI
        );
    }
    public void optionalExmaples() {
        final Motorbike motorbike = createAndSaveVehicle(1).get(0);
        final String id = motorbike.getId();

        orElseThrow(id);
        or(id);
        filter(id);
        map(id);
        ifPresentOrElse(id);
    }

    private void orElseThrow(String id) {
        final Motorbike motorbike1 = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find motorbike with id " + id));
        System.out.println(motorbike1.getModel());

        System.out.println("~".repeat(10));

        try {
            final Motorbike motorbike2 = repository.findById("123")
                    .orElseThrow(() -> new IllegalArgumentException("Cannot find motorbike with id " + "123"));
            System.out.println(motorbike2.getModel());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void or(String id) {
        final Optional<Motorbike> motorbike1 = repository.findById(id).or(() -> Optional.of(createMotorbike()));
        motorbike1.ifPresent(motorbike -> {
            System.out.println(motorbike.getModel());
        });
        System.out.println("~".repeat(10));

        final Optional<Motorbike> motorbike2 = repository.findById("123").or(() -> Optional.of(createMotorbike()));
        motorbike2.ifPresent(motorbike -> {
            System.out.println(motorbike.getModel());
        });
    }

    private void filter(String id) {
        repository.findById(id)
                .filter(motorbike -> !motorbike.getManufacturerMotorbike().equals(""))
                .ifPresent(motorbike -> {
                    System.out.println(motorbike.getModel());
                });

        repository.findById(id)
                .filter(motorbike -> motorbike.getManufacturerMotorbike().equals(""))
                .ifPresent(motorbike -> {
                    System.out.println(motorbike.getModel());
                });
    }

    private void map(String id) {
        repository.findById(id)
                .map(Vehicle::getModel)
                .ifPresent(System.out::println);
    }

    private void ifPresentOrElse(String id) {
        repository.findById(id).ifPresentOrElse(
                motorbike -> {
                    System.out.println(motorbike.getModel());
                },
                () -> {
                    System.out.println("Cannot find motorbike with id " + "123");
                }
        );

        repository.findById("123").ifPresentOrElse(
                motorbike -> {
                    System.out.println(motorbike.getModel());
                },
                () -> {
                    System.out.println("Cannot find motorbike with id " + "123");
                }
        );
    }
}