package com.repository;

import com.model.Bus;

import java.math.BigDecimal;
import java.util.*;

public class BusRepository implements CrudRepository<Bus> {
    private final List<Bus> buses;

    public BusRepository() {
        buses = new LinkedList<>();
    }

    @Override
    public Optional <Bus> findById(String id) {
        for (Bus bus : buses) {
            if (bus.getId().equals(id)) {
                return Optional.of(bus);
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Bus> getAll() {
        return buses;
    }

    @Override
    public boolean save(Bus bus) {
        if (bus == null) {
            throw new IllegalArgumentException("Bus must not be null");
        }
        if (bus.getPrice().equals(BigDecimal.ZERO)) {
            bus.setPrice(BigDecimal.valueOf(-1));
        }
        buses.add(bus);
        return true;
    }

    @Override
    public boolean saveAll(List<Bus> bus) {
        if (bus == null) {
            return false;
        }
        return buses.addAll(bus);
    }

    @Override
    public boolean update(Bus bus) {
        final Optional<Bus> optionalBus = findById(bus.getId());
        if (optionalBus.isPresent()) {
            optionalBus.ifPresent(founded -> BusRepository.BusCopy.copy(bus, founded));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Bus> iterator = buses.iterator();
        while (iterator.hasNext()) {
            final Bus bus = iterator.next();
            if (bus.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateBusRoute(int busRoute, Bus copyFrom) {
        for (Bus bus : buses) {
            if (Objects.equals(bus.getBusRoute(), busRoute)) {
                BusRepository.BusCopy.copy(copyFrom, bus);
            }
        }
        return true;
    }

    public boolean updatePassengersNumber(int passengersNumber, Bus copyFrom) {
        for (Bus bus : buses) {
            if (Objects.equals(bus.getPassengersNumber(), passengersNumber)) {
                BusRepository.BusCopy.copy(copyFrom, bus);
            }
            ;

        }
        return true;
    }


    private static class BusCopy {
        static void copy(final Bus from, final Bus to) {
            to.setModel(from.getModel());
            to.setBusRoute(from.getBusRoute());
            to.setPassengersNumber(from.getPassengersNumber());
            to.setPrice(from.getPrice());
        }
    }
}
