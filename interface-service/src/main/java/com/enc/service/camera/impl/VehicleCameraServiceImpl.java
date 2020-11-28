package com.enc.service.camera.impl;

import com.alibaba.fastjson.JSONObject;
import com.enc.domain.platform.entity.kafka.VehicleEntity;
import com.enc.kafka.websocket.WebSocketServer;
import com.enc.service.camera.VehicleCameraService;
import com.enc.service.kafka.VehicleService;
import com.enc.utils.base64img.ConvertBase64ToImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: BOND
 * @description:
 * @create: 2019-06-01 00:30
 */
@Slf4j
@Service
public class VehicleCameraServiceImpl implements VehicleCameraService {

    @Value("${patch.url}")
    private String localUrl;

    @Value("${patch.vehicle.data-url}")
    private String dataUrl;

    @Autowired
    VehicleService vehicleService;

    @Override
    public void vehicleDataSaveSend(JSONObject vJson) {

        // JSONObject vJson = JSONObject.parseObject(body);
        SimpleDateFormat folderSdf = new SimpleDateFormat("yyyyMMdd");

        String dateFolder = folderSdf.format(new Date())+"/";
        String imgName = UUID.randomUUID()+".jpg";
        String localPath = localUrl+dataUrl+dateFolder;
        String dataPath = dataUrl+dateFolder;
        ConvertBase64ToImage.generateImage(localPath,imgName,vJson.getString("picture"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        VehicleEntity entity = new VehicleEntity();
        entity.setId(vehicleService.maxId());
        entity.setCarLogo(vJson.getString("car_logo"));
        entity.setStartTime(sdf.format(new Date(vJson.getLong("start_time") * 1000)));
        entity.setCarPlate(vJson.getString("car_plate"));
        entity.setColor(vJson.getString("color"));
        entity.setParkId(vJson.getString("park_id"));
        entity.setCameraId(vJson.getString("camera_id"));
        entity.setType(vJson.getString("type"));
        entity.setPicture(dataPath+""+imgName);
        // entity.setPicture("data:image/jpg;base64,"+vJson.getString("picture").replace(" ","+"));
        entity.setCreateDate(sdf.format(new Date()));

        vehicleService.addVehicle(entity);

        try {
            vJson.put("id",entity.getId());
            vJson.put("picture",entity.getPicture());
            vJson.put("web_socket_type","vehicle");
            vJson.put("start_time",entity.getStartTime());
            WebSocketServer.sendInfo(vJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}