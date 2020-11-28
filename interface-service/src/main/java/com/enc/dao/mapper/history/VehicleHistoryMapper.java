package com.enc.dao.mapper.history;

import com.enc.domain.platform.vo.history.vehicle.VehicleListQueryVo;

import java.util.List;
import java.util.Map;

public interface VehicleHistoryMapper {
    /***
     * 获取过往车辆列表Sql
     * @param vo
     * @return
     */
    List<Map<String,Object>> getHisVehicleListSql(VehicleListQueryVo vo);

    List<Map<String,Object>> getHisVehicleTopSql(String date);

}
