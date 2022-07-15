package com.repository;

import com.model.Motorbike;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MotorbikeRepository implements CrudRepository<Motorbike> {

    private final List<Motorbike> motorbikes;

    public MotorbikeRepository() {
        motorbikes = new ArrayList<>();
    }

    @Override
    public Motorbike getById(String id) {
        for (Motorbike motorbike : motorbikes) {
            if (motorbike.getId().equals(id)) {
                return motorbike;
            }
        }
        return null;
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
        final Motorbike founded = getById(motorbike.getId());
        if (founded != null) {
            MotorbikeRepository.MotorbikeCopy.copy(motorbike, founded);
            return true;
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
