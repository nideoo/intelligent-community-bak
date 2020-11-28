package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.dao.mapper.DevicesMsMapper;
import com.enc.domain.ResponseResult;
import com.enc.domain.access.entity.DevicesMsEntity;
import com.enc.domain.access.vo.term.DeviceMsTermVo;
import com.enc.service.DevicesMsService;
import com.enc.service.MsTokenService;
import com.enc.utils.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-23 11:15
 */
@Service
public class DevicesMsServiceImpl implements DevicesMsService {

    @Autowired
    DevicesMsMapper devicesMsMapper;

    @Autowired
    MsTokenService msTokenService;

    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;

    @Override
    public ResponseResult getDeviceMsBatch() {

        DeviceMsTermVo vo = new DeviceMsTermVo();
        vo.setProjectID(PROJECTID);
        vo.setDeviceTyp("");
        vo.setToken(msTokenService.getToKen());
        String jsonText = JSON.toJSONString(vo);
        JSONObject tjson = JSONObject.parseObject(jsonText);
        String rStr = HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_DEVICE_MS_URL, tjson,"utf-8");
        JSONObject rJson = JSONObject.parseObject(rStr);
        JSONArray jsonArr = rJson.getJSONArray("devices");

        for(int i = 0; i < jsonArr.size(); i++){
            JSONObject json = jsonArr.getJSONObject(i);
            DevicesMsEntity entity = new DevicesMsEntity();
            entity.setId(json.getInteger("id"));
            entity.setName(json.getString("name"));
            entity.setDevicePath(json.getString("devicePath"));
            entity.setDeviceTyp(json.getString("deviceTyp"));
            devicesMsMapper.addMsDevice(entity);
        }

        return null;
    }
}















