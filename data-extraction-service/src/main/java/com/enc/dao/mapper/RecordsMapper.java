package com.enc.dao.mapper;

import com.enc.domain.access.entity.RoomEntity;
import com.enc.domain.access.entity.TRecords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecordsMapper {

    void addTRecords(TRecords records);
}
