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
@Api("接嘉陵区公安局门口人脸相机数据")
@Slf4j
@RestController
@RequestMapping(value = "/kafka-produce")
public class ProduceFaceController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @ApiOperation("接收相机数据")
    @RequestMapping(value = "/accept-face", method = RequestMethod.POST)
    public ResponseResult acceptFace(@RequestParam Map<String, Object> mapObject) {
        JSONObject fJson = JSONObject.parseObject((mapObject.toString().replace("{accessRecord=","").replace("=}","")).toString());
        try {
            ListenableFuture future = kafkaTemplate.send(KafkaTopicStatic.DEVICE_CAMERA_FACE_JLQJCJ_TOPIC, fJson.toString());
            future.addCallback(o -> log.info("人脸消息发送成功：" + fJson), throwable -> log.info("人脸消息发送失败：" + fJson));
            return ResponseResult.success(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.fail(ex);
        }
    }
}














