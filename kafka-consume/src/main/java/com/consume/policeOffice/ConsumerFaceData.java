package com.consume.policeOffice;

import com.enc.constants.KafkaTopicStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-16 16:43
 */
@Component
public class ConsumerFaceData {


    private static Logger log = LoggerFactory.getLogger(ConsumerFaceData.class);

    /***
     * 接收人脸相机数据
     * @param body
     */
    @KafkaListener(topics = KafkaTopicStatic.DEVICE_CAMERA_FACE_JLQJCJ_TOPIC)
    public void receiveFaceData(@Payload String body) {
        log.info("--------接收人脸相机数据>>>>>---:"+body);
    }
}
