package com.enc.kafka.consume.policeOffice;

import com.alibaba.fastjson.JSONObject;
import com.enc.constants.KafkaTopicStatic;
import com.enc.domain.platform.entity.kafka.VehicleEntity;
import com.enc.kafka.websocket.WebSocketServer;
import com.enc.service.kafka.VehicleService;
import com.enc.utils.base64img.ConvertBase64ToImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-16 16:43
 */
@Component
public class ConsumerVehicleData {


    private static Logger log = LoggerFactory.getLogger(ConsumerVehicleData.class);

    @Value("${patch.vehicle.local-url}")
    private String localUrl;

    @Value("${patch.vehicle.data-url}")
    private String dataUrl;

    @Autowired
    VehicleService vehicleService;

    /***
     * 接收人脸相机数据
     * @param body
     */
    /*
    @KafkaListener(topics = KafkaTopicStatic.DEVICE_CAMERA_VEHICLE_JLQJCJ_TOPIC)
    public void receiveFaceData(@Payload String body) {
        JSONObject vJson = JSONObject.parseObject(body);

        SimpleDateFormat folderSdf = new SimpleDateFormat("yyyyMMdd");
        String dateFolder = folderSdf.format(new Date())+"/";
        String imgName = UUID.randomUUID()+".jpg";

        String localPath = localUrl+dateFolder;
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
        entity.setCreateDate(sdf.format(new Date()));

        vehicleService.addVehicle(entity);

        try {
            vJson.put("id",entity.getId());
            vJson.put("picture",entity.getPicture());
            vJson.put("web_socket_type","vehicle");
            WebSocketServer.sendInfo(vJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("--------接收车辆相机数据>>>>>---:"+vJson.toString());
    }
    */
}
























