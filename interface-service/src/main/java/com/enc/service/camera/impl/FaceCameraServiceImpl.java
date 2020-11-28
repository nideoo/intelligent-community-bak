package com.enc.service.camera.impl;

import com.alibaba.fastjson.JSONObject;
import com.enc.domain.platform.entity.kafka.FaceEntity;
import com.enc.kafka.websocket.WebSocketServer;
import com.enc.service.camera.FaceCameraService;
import com.enc.service.kafka.FaceService;
import com.enc.utils.base64img.ConvertBase64ToImage;
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
@Service
public class FaceCameraServiceImpl implements FaceCameraService {

    @Value("${patch.url}")
    private String localUrl;

    @Value("${patch.face.data-url}")
    private String dataUrl;

    @Autowired
    FaceService faceService;

    @Override
    public void faceDataSaveSend(JSONObject fJson) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat folderSdf = new SimpleDateFormat("yyyyMMdd");
        String dateFolder = folderSdf.format(new Date())+"/";
        // JSONObject fJson = JSONObject.parseObject(body);
        String imageName = UUID.randomUUID()+".jpg";
        String bgImageName = UUID.randomUUID()+".jpg";
        String sampleImageName = UUID.randomUUID()+".jpg";

        String localPath = localUrl+dataUrl+dateFolder;
        String dataPath = dataUrl+dateFolder;

        ConvertBase64ToImage.generateImage(localPath,imageName,fJson.getString("image"));
        ConvertBase64ToImage.generateImage(localPath,bgImageName,fJson.getString("bg_image"));
        ConvertBase64ToImage.generateImage(localPath,sampleImageName,fJson.getString("sample_image"));


        FaceEntity entity = new FaceEntity();
        entity.setId(faceService.maxId());
        entity.setFaceId(fJson.getString("faceId"));
        entity.setAct(fJson.getString("act"));
        entity.setDatetime(sdf.format(new Date(fJson.getLong("datetime") * 1000)));


        entity.setScoreRecg(fJson.getString("score_recg"));
        entity.setUsername(fJson.getString("username"));
        entity.setAge(fJson.getString("age"));
        entity.setSex(fJson.getString("sex"));
        entity.setIdentityNum(fJson.getString("identity_num"));
        entity.setPhone(fJson.getString("phone"));

        entity.setImage(dataPath+imageName);
        entity.setBgImage(dataPath+bgImageName);
        entity.setSampleImage(dataPath+sampleImageName);

/*
        String title = "data:image/jpg;base64,";
        entity.setImage(title+fJson.getString("image").replace(" ","+"));
        entity.setBgImage(title+fJson.getString("bg_image").replace(" ","+"));
        entity.setSampleImage(title+fJson.getString("sample_image").replace(" ","+"));
*/
        entity.setMac(fJson.getString("mac"));
        entity.setIp(fJson.getString("ip"));
        entity.setCreateDate(sdf.format(new Date()));
        faceService.addFace(entity);
        if(!entity.getUsername().equals("未知")){
            try {
                fJson.put("id",entity.getId());
                fJson.put("image",entity.getImage());
                fJson.put("bg_image",entity.getBgImage());
                fJson.put("sample_image",entity.getSampleImage());
                fJson.put("web_socket_type","face");
                fJson.put("datetime",entity.getDatetime());
                WebSocketServer.sendInfo(fJson.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}