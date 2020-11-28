package com.enc.dao.mapper;

import com.enc.domain.access.entity.RoomEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomMapper {

    /***
     * 添加房屋
     * @param entity
     * @return
     */
    int addRoom(RoomEntity entity);

    int getRoomById(String id);

    List<Map<String,Object>> getRoomByIdSumSql();

    int getRoomSumCount(String id);

    void addRoomSum(Map map);

    void updateRoomSum(Map map);

    void delRoomByIdSql(int id);

}
