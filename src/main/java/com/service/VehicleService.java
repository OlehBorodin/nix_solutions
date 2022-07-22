package com.service;

import com.model.Vehicle;
import com.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class VehicleService <T extends Vehicle>{
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
            LOGGER.debug("Created vehicle {}", vehicle.getId());
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

    public Optional<T> findOneById(String id) {
        return id == null ? repository.findById("") : repository.findById(id);
    }
/*
    private Auto cretaOne() {
        return new Auto(
                "Model new",
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                getRandomManufacturer(),
                BodyType.CONVERTIBLE
        );
    }
    public void optionalExmaples() {
        final Auto auto = createAndSaveAutos(1).get(0);
        final String id = auto.getId();

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
        final Optional<Auto> autoOptional1 = repository.findById(id);
        autoOptional1.ifPresent(auto -> System.out.println(auto.getModel()));

        final Optional<Auto> autoOptional2 = repository.findById("123");
        autoOptional2.ifPresent(auto -> System.out.println(auto.getModel()));

        if (autoOptional2.isEmpty()) {
            System.out.println("Auto with id \"123\" not found");
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
        final Auto auto1 = repository.findById(id).orElse(cretaOne());
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        final Auto auto2 = repository.findById("123").orElse(cretaOne());
        System.out.println(auto2.getModel());
    }

    private void orElseThrow(String id) {
        final Auto auto1 = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find auto with id " + id));
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        try {
            final Auto auto2 = repository.findById("123")
                    .orElseThrow(() -> new IllegalArgumentException("Cannot find auto with id " + "123"));
            System.out.println(auto2.getModel());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void or(String id) {
        final Optional<Auto> auto1 = repository.findById(id).or(() -> Optional.of(cretaOne()));
        auto1.ifPresent(auto -> {
            System.out.println(auto.getModel());
        });

        System.out.println("~".repeat(10));

        final Optional<Auto> auto2 = repository.findById("123").or(() -> Optional.of(cretaOne()));
        auto2.ifPresent(auto -> {
            System.out.println(auto.getModel());
        });
    }

    private void orElseGet(String id) {
        final Auto auto = repository.findById(id).orElse(cretaOne());
        final Auto auto1 = repository.findById(id).orElseGet(() -> cretaOne());
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        final Auto auto2 = repository.findById("123").orElseGet(() -> {
            System.out.println("Cannot find auto with id " + "123");
            return cretaOne();
        });
        System.out.println(auto2.getModel());
    }

    private void filter(String id) {
        repository.findById(id)
                .filter(auto -> !auto.getBodyType().equals(""))
                .ifPresent(auto -> {
                    System.out.println(auto.getModel());
                });

        repository.findById(id)
                .filter(auto -> auto.getBodyType().equals(""))
                .ifPresent(auto -> {
                    System.out.println(auto.getModel());
                });
    }

    private void map(String id) {
        repository.findById(id)
                .map(Vehicle::getModel)
                .ifPresent(System.out::println);
    }

    private void ifPresentOrElse(String id) {
        repository.findById(id).ifPresentOrElse(
                auto -> {
                    System.out.println(auto.getModel());
                },
                () -> {
                    System.out.println("Cannot find auto with id " + "123");
                }
        );

        repository.findById("123").ifPresentOrElse(
                auto -> {
                    System.out.println(auto.getModel());
                },
                () -> {
                    System.out.println("Cannot find auto with id " + "123");
                }
        );
    }
*/
    public boolean update(T vehicle) {
        if(repository.findById(vehicle.getId()).isPresent()){
            LOGGER.debug("Update vehicle {}", vehicle.getId());
        }
        return repository.update(vehicle);
    }
    public boolean delete(T vehicle) {
        if(repository.delete(vehicle.getId())){
            LOGGER.debug("Delete vehicle {}", vehicle.getId());
            return true;
        }
        return false;
    }


}
