package com.enc.controller.history;

import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.domain.platform.vo.history.vehicle.VehicleListQueryVo;
import com.enc.service.history.VehicleHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-21 22:20
 */
@RestController
@Api(value = "历史数据-卡口-过往车辆")
@RequestMapping("/api/")
public class VehicleHistoryContoller {

    @Autowired
    VehicleHistoryService vehicleHistoryService;

    @ApiOperation(value="过往车辆列表", notes="", httpMethod="POST")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/his-vehicle-list", method = RequestMethod.POST)
    public ResponseResult hisVehicleList(@RequestBody VehicleListQueryVo vo){
        return ResponseResult.success(vehicleHistoryService.getHisVehicleList(vo));
    }

}







