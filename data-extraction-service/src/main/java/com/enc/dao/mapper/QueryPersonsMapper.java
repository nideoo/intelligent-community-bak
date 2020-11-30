package com.enc.dao.mapper;

import com.enc.domain.access.entity.QueryPersonsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zk-bond
 * @version 1.0
 * @describe
 * @date 2020/11/28 14:17
 */
@Mapper
public interface QueryPersonsMapper {

    void addQueryPersons(QueryPersonsEntity entity);

}
