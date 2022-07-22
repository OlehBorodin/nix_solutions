package com.repository;

import com.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Vehicle> {
    Optional <T> findById(String id);

    List<T> getAll();

    boolean save(T temporary);

    boolean saveAll(List<T> temporary);

    boolean update(T temporary);

    boolean delete(String id);
}
