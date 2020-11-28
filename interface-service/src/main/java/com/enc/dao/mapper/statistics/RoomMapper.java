package com.enc.dao.mapper.statistics;

import com.enc.domain.platform.vo.statistics.RoomListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomMapper {

    List<Map<String,Object>> getRoomListSql(RoomListVo vo);

    List<Map<String,Object>> getStreetCommByIdListSql(String id);

    List<Map<String,Object>> getBuildingsByIdListSql(String id);

    Map<String,Object> getRoomInfo(String id);

}
