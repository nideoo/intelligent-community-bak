package com.produce.controller.policeOffice;

import com.alibaba.fastjson.JSONObject;
import com.enc.constants.KafkaTopicStatic;
import com.enc.domain.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-16 16:43
 */
@Api("接嘉陵区公安局门口车辆相机数据")
@Slf4j
@RestController
@RequestMapping(value = "/kafka-produce")
public class ProduceVehicleController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @ApiOperation("接收相机数据")
    @RequestMapping(value = "/accept-vehicle", method = RequestMethod.POST)
    public ResponseResult acceptVehicle(@RequestParam Map<String, Object> mapParameter) {
        JSONObject rJson = new JSONObject();
        rJson.put("type",mapParameter.get("type"));
        rJson.put("car_plate",mapParameter.get("car_plate"));
        rJson.put("car_logo",mapParameter.get("car_logo"));
        rJson.put("color",mapParameter.get("color"));
        rJson.put("start_time",mapParameter.get("start_time"));
        rJson.put("park_id",mapParameter.get("park_id"));
        rJson.put("camera_id",mapParameter.get("camera_id"));
        rJson.put("picture",mapParameter.get("picture"));
        try {
            ListenableFuture future = kafkaTemplate.send(KafkaTopicStatic.DEVICE_CAMERA_VEHICLE_JLQJCJ_TOPIC, rJson.toString());
            future.addCallback(o -> log.info("车辆消息发送成功：" + rJson), throwable -> log.info("车辆消息发送失败：" + rJson));
            return ResponseResult.success(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.fail(ex);
        }
    }

}














