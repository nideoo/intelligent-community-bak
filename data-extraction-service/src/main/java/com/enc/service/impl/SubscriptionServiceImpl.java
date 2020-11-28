package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.service.MsTokenService;
import com.enc.service.SubscriptionService;
import com.enc.utils.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-23 18:23
 */
@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    MsTokenService msTokenService;

    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;

    @Value("${sube.url}")
    private String SUBE_URL;

    @Override
    public void setSubscribe() {
        JSONObject tJson = new JSONObject();
        tJson.put("projectID",PROJECTID);
        tJson.put("token",msTokenService.getToKen());
        // tJson.put("personChangeURL",SUBE_URL+IntelligentCommunityStatic.PUSH_PERSONS_URL);
        tJson.put("personChangeURL","");
        tJson.put("deviceStatusChangeURL",SUBE_URL+IntelligentCommunityStatic.PUSH_DEVICE_STATUS_URL);
        tJson.put("uploadAlarmURL",SUBE_URL+IntelligentCommunityStatic.PUSH_ALARM_INCIDENTS_URL);
        tJson.put("uploadCardURL",SUBE_URL+IntelligentCommunityStatic.PUSH_READ_CARD_EVENT_URL);
        tJson.put("distributionControlURL",SUBE_URL+IntelligentCommunityStatic.PUSH_DISTRIBUTION_URL);
        tJson.put("captureURL",SUBE_URL+IntelligentCommunityStatic.PUSH_CAPTURE_URL);

        tJson.put("uploadRecordsURL",SUBE_URL+IntelligentCommunityStatic.PUSH_RECORDS_URL);
        // tJson.put("uploadRecordsURL","http://60.255.139.167:8082/push/upload-records");

        tJson.put("objectChangeURL",SUBE_URL+IntelligentCommunityStatic.PUSH_OBJECT_URL);

        log.info("tJson:"+tJson.toString());

        HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_SUBSCRIPTION_URL, tJson,"utf-8");
    }
}














