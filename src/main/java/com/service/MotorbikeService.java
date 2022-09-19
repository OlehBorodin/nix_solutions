package com.service;

import com.model.ManufacturerMotorbike;
import com.model.Motorbike;
import com.repository.CrudRepository;
import com.repository.MotorbikeRepository;

import java.math.BigDecimal;

public class MotorbikeService extends VehicleService<Motorbike> {

    private static MotorbikeService instance;
    public MotorbikeService(CrudRepository<Motorbike> repository) {
        super(repository);
    }

    public static MotorbikeService getInstance(){
        if(instance==null){
            instance = new MotorbikeService(MotorbikeRepository.getInstance());
        }
        return instance;
    }
    @Override
    protected Motorbike create() {
        return new Motorbike("Motorbike model", BigDecimal.valueOf(2000), ManufacturerMotorbike.HONDA,1);
    }


    protected Motorbike cretaOne() {
        return new Motorbike(
                "Model new Motorbike",
                BigDecimal.valueOf(10000),
                ManufacturerMotorbike.KAWASAKI,
                1
        );
    }
}