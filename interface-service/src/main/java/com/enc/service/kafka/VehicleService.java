package com.enc.service.kafka;

import com.enc.domain.platform.entity.kafka.VehicleEntity;

public interface VehicleService {

    int maxId();

    void addVehicle(VehicleEntity entity);

}
