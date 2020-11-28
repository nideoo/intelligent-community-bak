package com.enc.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsonMapper {


    List<Map<String,Object>> getCommunity();

    List<Map<String, Object>> getBuildings();

    List<Map<String, Object>> getRoom();

    List<Map<String, Object>> getPerson(@Param("start") Integer start, @Param("size") Integer size);

    List<Map<String, Object>> getRecords(String time);

    String getYxq();
}
