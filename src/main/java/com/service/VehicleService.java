package com.service;

import com.model.Vehicle;
import com.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class VehicleService<T extends Vehicle> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    protected static final Random RANDOM = new Random();
    protected final CrudRepository<T> repository;

    public VehicleService(CrudRepository<T> repository) {
        this.repository = repository;
    }


    public List<T> createAndSaveVehicle(int count) {
        List<T> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final T vehicle = create();
            result.add(vehicle);
            repository.save(vehicle);
            LOGGER.info("Created vehicle {}", vehicle.getId());
        }
        return result;
    }

    protected abstract T create();


    public boolean saveVehicle(List<T> vehicles) {
        repository.saveAll(vehicles);
        return false;
    }

    public void save(T vehicle) {
        repository.save(vehicle);
    }


    public void printAll() {
        for (Vehicle vehicle : repository.getAll()) {
            System.out.println(vehicle);
        }
    }

    public boolean update(T vehicle) {
        if (repository.findById(vehicle.getId()).isPresent()) {
            LOGGER.info("Update vehicle {}", vehicle.getId());
        }
        return repository.update(vehicle);

    }

    public void updateVehicleByPrice(String id, BigDecimal price) {
        Optional<T> vehicle = repository.findById(id);
        vehicle.ifPresent(vehicle1 -> {
            vehicle1.setPrice(price);
            repository.update(vehicle1);
        });
    }

    public void delete(String id) {
        repository.delete(id);
        LOGGER.info("Deleted auto {}", id);
    }

    public boolean delete(T vehicle) {
        if (repository.delete(vehicle.getId())) {
            LOGGER.info("Delete vehicle {}", vehicle.getId());
            return true;
        }
        return false;
    }

    public List<T> getAll() {
        return repository.getAll();
    }


    public Optional<T> findOneById(String id) {
        return id == null ? repository.findById("") : repository.findById(id);
    }

    protected abstract T cretaOne();

    public void optionalExmaples() {
        final Vehicle vehicle = createAndSaveVehicle(1).get(0);
        final String id = vehicle.getId();

        isPresent(id);
        ifPresent(id);
        orElse(id);
        orElseThrow(id);
        or(id);
        orElseGet(id);
        filter(id);
        map(id);
        ifPresentOrElse(id);

    }

    private void isPresent(String id) {
        final Optional<T> vehicleOptional1 = repository.findById(id);
        vehicleOptional1.ifPresent(auto -> System.out.println(auto.getModel()));

        final Optional<T> vehicleOptional2 = repository.findById("123");
        vehicleOptional2.ifPresent(auto -> System.out.println(auto.getModel()));

        if (vehicleOptional2.isEmpty()) {
            System.out.println("Vehicle with id \"123\" not found");
        }
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
        final Vehicle vehicle1 = repository.findById(id).orElse(cretaOne());
        System.out.println(vehicle1.getModel());

        System.out.println("~".repeat(10));

        final Vehicle vehicle2 = repository.findById("123").orElse(cretaOne());
        System.out.println(vehicle2.getModel());
    }

    private void orElseThrow(String id) {
        final Vehicle vehicle1 = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find vehicle with id " + id));
        System.out.println(vehicle1.getModel());

        System.out.println("~".repeat(10));

        try {
            final Vehicle vehicle2 = repository.findById("123")
                    .orElseThrow(() -> new IllegalArgumentException("Cannot find vehicle with id " + "123"));
            System.out.println(vehicle2.getModel());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void or(String id) {
        final Optional<T> vehicle1 = repository.findById(id).or(() -> Optional.of(cretaOne()));
        vehicle1.ifPresent(auto -> {
            System.out.println(auto.getModel());
        });

        System.out.println("~".repeat(10));

        final Optional<T> vehicle2 = repository.findById("123").or(() -> Optional.of(cretaOne()));
        vehicle2.ifPresent(auto -> {
            System.out.println(auto.getModel());
        });
    }

    private void orElseGet(String id) {
        final Vehicle vehicle = repository.findById(id).orElse(cretaOne());
        final Vehicle vehicle1 = repository.findById(id).orElseGet(() -> cretaOne());
        System.out.println(vehicle1.getModel());

        System.out.println("~".repeat(10));

        final Vehicle vehicle2 = repository.findById("123").orElseGet(() -> {
            System.out.println("Cannot find vehicle with id " + "123");
            return cretaOne();
        });
        System.out.println(vehicle2.getModel());
    }

    private void filter(String id) {
        repository.findById(id)
                .filter(vehicle -> !vehicle.getModel().equals(""))
                .ifPresent(vehicle -> {
                    System.out.println(vehicle.getModel());
                });

        repository.findById(id)
                .filter(vehicle -> vehicle.getModel().equals(""))
                .ifPresent(vehicle -> {
                    System.out.println(vehicle.getModel());
                });
    }

    private void map(String id) {
        repository.findById(id)
                .map(Vehicle::getModel)
                .ifPresent(System.out::println);
    }

    private void ifPresentOrElse(String id) {
        repository.findById(id).ifPresentOrElse(
                vehicle -> {
                    System.out.println(vehicle.getModel());
                },
                () -> {
                    System.out.println("Cannot find vehicle with id " + "123");
                }
        );

        repository.findById("123").ifPresentOrElse(
                vehicle -> {
                    System.out.println(vehicle.getModel());
                },
                () -> {
                    System.out.println("Cannot find vehicle with id " + "123");
                }
        );
    }


}
