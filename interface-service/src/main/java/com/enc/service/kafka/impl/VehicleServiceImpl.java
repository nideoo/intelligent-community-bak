package com.enc.service.kafka.impl;

import com.enc.dao.mapper.kafka.VehicleMapper;
import com.enc.domain.platform.entity.kafka.VehicleEntity;
import com.enc.service.kafka.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-20 13:57
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleMapper vehicleMapper;

    @Override
    public int maxId() {
        return vehicleMapper.maxId();
    }

    @Override
    public void addVehicle(VehicleEntity entity) {
        vehicleMapper.addVehicle(entity);
    }
}





















