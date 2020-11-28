package com.enc.service.history;

import com.enc.domain.platform.vo.history.vehicle.VehicleListQueryVo;
import com.github.pagehelper.PageInfo;

public interface VehicleHistoryService {

    /***
     * 获取过往车辆列表
     * @param vo
     * @return
     */
    PageInfo getHisVehicleList(VehicleListQueryVo vo);

}
