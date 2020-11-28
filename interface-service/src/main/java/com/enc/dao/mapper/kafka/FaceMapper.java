package com.enc.dao.mapper.kafka;

import com.enc.domain.platform.entity.kafka.FaceEntity;
import org.apache.ibatis.annotations.Mapper;

/***
 *
 */
@Mapper
public interface FaceMapper {

    int maxId();

    void addFace(FaceEntity entity);

}
