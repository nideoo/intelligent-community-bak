package com.enc.service.history.impl;

import com.enc.dao.mapper.history.VehicleHistoryMapper;
import com.enc.domain.platform.vo.history.vehicle.VehicleListQueryVo;
import com.enc.service.history.VehicleHistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-21 22:21
 */
@Service
public class VehicleHistoryServiceImpl implements VehicleHistoryService {

    @Autowired
    VehicleHistoryMapper vehicleHistoryMapper;

    @Override
    public PageInfo getHisVehicleList(VehicleListQueryVo vo) {
        PageHelper.startPage(vo.getPageIndex(),vo.getPageSize());
        List<Map<String,Object>> list = vehicleHistoryMapper.getHisVehicleListSql(vo);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}