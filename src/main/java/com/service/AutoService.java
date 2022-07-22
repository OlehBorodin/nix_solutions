package com.service;

import com.model.Auto;
import com.model.BodyType;
import com.model.ManufacturerAuto;
import com.model.Vehicle;
import com.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class AutoService extends VehicleService <Auto> {



    public AutoService(CrudRepository<Auto> repository) {
        super(repository);
    }


    private ManufacturerAuto getRandomManufacturer() {
        final ManufacturerAuto[] values = ManufacturerAuto.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    private BodyType getBodyType() {
        final BodyType[] values = BodyType.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }


    @Override
    protected Auto create() {
        return new Auto("M5", BigDecimal.valueOf(RANDOM.nextInt(1000)),
                ManufacturerAuto.BMW,BodyType.SEDAN);
    }

    public Optional<Auto> findOneById(String id) {
        return id == null ? repository.findById("") : repository.findById(id);
    }

    private Auto cretaOne() {
        return new Auto(
                "Model new",
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                getRandomManufacturer(),
                BodyType.CONVERTIBLE
        );
    }
    public void optionalExmaples() {
        final Auto auto = createAndSaveVehicle(1).get(0);
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
}



