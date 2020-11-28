package com.enc.service.kafka.impl;

import com.enc.dao.mapper.kafka.FaceMapper;
import com.enc.domain.platform.entity.kafka.FaceEntity;
import com.enc.service.kafka.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-20 13:57
 */
@Service
public class FaceServiceImpl implements FaceService {

    @Autowired
    FaceMapper faceMapper;

    @Override
    public int maxId() {
        return faceMapper.maxId();
    }

    @Override
    public void addFace(FaceEntity entity) {
        faceMapper.addFace(entity);
    }
}