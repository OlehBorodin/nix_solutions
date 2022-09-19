package com.repository;

import com.model.Motorbike;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MotorbikeRepository implements CrudRepository<Motorbike> {

    public static MotorbikeRepository instance;
    private final List<Motorbike> motorbikes;

    public MotorbikeRepository() {
        motorbikes = new ArrayList<>();
    }


    public static MotorbikeRepository getInstance(){
        if(instance==null){
            instance = new MotorbikeRepository();
        }
        return instance;
    }

    @Override
    public Optional <Motorbike> findById(String id) {
        for (Motorbike motorbike : motorbikes) {
            if (motorbike.getId().equals(id)) {
                return Optional.of(motorbike);
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Motorbike> getAll() {
        return motorbikes;
    }

    @Override
    public boolean save(Motorbike motorbike) {
        if (motorbike == null) {
            throw new IllegalArgumentException("Motorbike must not be null");
        }
        if (motorbike.getPrice().equals(BigDecimal.valueOf(1233333))) {
            motorbike.setPrice(BigDecimal.valueOf(-1));
        }
        motorbikes.add(motorbike);
        return true;
    }

    @Override
    public boolean saveAll(List<Motorbike> motorbike) {
        if (motorbike == null) {
            return false;
        }
        return motorbikes.addAll(motorbike);
    }

    @Override
    public boolean update(Motorbike motorbike) {
        final Optional<Motorbike> optionalMotorbike = findById(motorbike.getId());
        if (optionalMotorbike.isPresent()) {
            optionalMotorbike.ifPresent(founded -> MotorbikeRepository.MotorbikeCopy.copy(motorbike, founded));
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Motorbike> iterator = motorbikes.iterator();
        while (iterator.hasNext()) {
            final Motorbike motorbike = iterator.next();
            if (motorbike.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    private static class MotorbikeCopy {
        static void copy(final Motorbike from, final Motorbike to) {
            to.setModel(from.getModel());
            to.setPrice(from.getPrice());
        }
    }
}
