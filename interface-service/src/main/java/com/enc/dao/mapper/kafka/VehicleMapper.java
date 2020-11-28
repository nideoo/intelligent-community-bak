package com.enc.dao.mapper.kafka;

import com.enc.domain.platform.entity.kafka.VehicleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/***
 *
 */
@Mapper
public interface VehicleMapper {

    int maxId();

    void addVehicle(VehicleEntity entity);

}
