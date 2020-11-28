package com.enc.kafka.consume.policeOffice;

import com.alibaba.fastjson.JSONObject;
import com.enc.constants.KafkaTopicStatic;
import com.enc.domain.platform.entity.kafka.FaceEntity;
import com.enc.kafka.websocket.WebSocketServer;
import com.enc.service.kafka.FaceService;
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
public class ConsumerFaceData {


    private static Logger log = LoggerFactory.getLogger(ConsumerFaceData.class);

    @Value("${patch.face.local-url}")
    private String localUrl;

    @Value("${patch.face.data-url}")
    private String dataUrl;

    @Autowired
    FaceService faceService;

    /***
     * 接收人脸相机数据
     * @param body
     */
    /*
    @KafkaListener(topics = KafkaTopicStatic.DEVICE_CAMERA_FACE_JLQJCJ_TOPIC)
    public void receiveFaceData(@Payload String body) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat folderSdf = new SimpleDateFormat("yyyyMMdd");
        String dateFolder = folderSdf.format(new Date())+"/";
        JSONObject fJson = JSONObject.parseObject(body);
        String imageName = UUID.randomUUID()+".jpg";
        String bgImageName = UUID.randomUUID()+".jpg";
        String sampleImageName = UUID.randomUUID()+".jpg";

        String localPath = localUrl+dateFolder;
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
        entity.setMac(fJson.getString("mac"));
        entity.setIp(fJson.getString("ip"));
        entity.setCreateDate(sdf.format(new Date()));
        faceService.addFace(entity);

        try {
            fJson.put("id",entity.getId());
            fJson.put("image",entity.getImage());
            fJson.put("bg_image",entity.getBgImage());
            fJson.put("sample_image",entity.getSampleImage());
            fJson.put("web_socket_type","face");
            WebSocketServer.sendInfo(fJson.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}

























