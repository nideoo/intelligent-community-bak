package com.enc.service.kafka;


import com.enc.domain.platform.entity.kafka.FaceEntity;

public interface FaceService {

    int maxId();

    void addFace(FaceEntity entity);

}
