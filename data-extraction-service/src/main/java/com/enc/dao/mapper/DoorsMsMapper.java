package com.enc.dao.mapper;

import com.enc.domain.access.entity.DoorsMsEntity;

public interface DoorsMsMapper {

    void addMsDoors(DoorsMsEntity entity);

    int getCountById(String id);

}
