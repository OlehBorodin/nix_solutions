package com.repository;

import com.model.Vehicle;

import java.util.List;

public interface CrudRepository<Temporary extends Vehicle> {
    Temporary getById(String id);

    List<Temporary> getAll();

    boolean save(Temporary temporary);

    boolean saveAll(List<Temporary> temporary);

    boolean update(Temporary temporary);

    boolean delete(String id);
}
