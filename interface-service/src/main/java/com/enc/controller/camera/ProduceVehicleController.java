package com.enc.controller.camera;

import com.alibaba.fastjson.JSONObject;
import com.enc.constants.KafkaTopicStatic;
import com.enc.domain.ResponseResult;
import com.enc.service.camera.VehicleCameraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-16 16:43
 */
@Api("接嘉陵区公安局门口车辆相机数据")
@Slf4j
@RestController
@RequestMapping(value = "/camera-receive")
public class ProduceVehicleController {

    @Autowired
    VehicleCameraService vehicleCameraService;

    @ApiOperation("接收相机数据")
    @RequestMapping(value = "/vehicle", method = RequestMethod.POST)
    public ResponseResult acceptVehicle(@RequestParam Map<String, Object> mapParameter) {

        JSONObject vJson = new JSONObject();

        vJson.put("type",mapParameter.get("type"));
        vJson.put("car_plate",mapParameter.get("car_plate"));
        vJson.put("car_logo",mapParameter.get("car_logo"));
        vJson.put("color",mapParameter.get("color"));
        vJson.put("start_time",mapParameter.get("start_time"));
        vJson.put("park_id",mapParameter.get("park_id"));
        vJson.put("camera_id",mapParameter.get("camera_id"));
        vJson.put("picture",mapParameter.get("picture"));

        vehicleCameraService.vehicleDataSaveSend(vJson);

        return ResponseResult.success("vehicle");
    }

}














