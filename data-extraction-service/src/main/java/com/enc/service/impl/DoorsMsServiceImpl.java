package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.dao.mapper.DevicesMsMapper;
import com.enc.dao.mapper.DoorsMsMapper;
import com.enc.domain.ResponseResult;
import com.enc.domain.access.entity.DevicesMsEntity;
import com.enc.domain.access.entity.DoorsMsEntity;
import com.enc.domain.access.vo.term.DeviceMsTermVo;
import com.enc.domain.access.vo.term.DoorsMsTermVo;
import com.enc.service.DevicesMsService;
import com.enc.service.DoorsMsService;
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
public class DoorsMsServiceImpl implements DoorsMsService {

    @Autowired
    DoorsMsMapper doorsMsMapper;

    @Autowired
    MsTokenService msTokenService;

    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;

    @Override
    public ResponseResult getDoorsMsBatch() {

        DoorsMsTermVo vo = new DoorsMsTermVo();
        vo.setProjectID(PROJECTID);
        vo.setDoorTyp("");
        vo.setToken(msTokenService.getToKen());
        String jsonText = JSON.toJSONString(vo);
        JSONObject tjson = JSONObject.parseObject(jsonText);
        String rStr = HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_DOORS_MS_URL, tjson,"utf-8");
        JSONObject rJson = JSONObject.parseObject(rStr);
        JSONArray jsonArr = rJson.getJSONArray("doors");
        for(int i = 0; i < jsonArr.size(); i++) {
            JSONObject json = jsonArr.getJSONObject(i);
            DoorsMsEntity entity = new DoorsMsEntity();
            entity.setId(json.getInteger("id"));
            entity.setName(json.getString("name"));
            entity.setDoorTyp(json.getString("doorTyp"));
            entity.setStatus(json.getString("status"));
            JSONArray arr = json.getJSONArray("doorPath");
            for(int j = 0; j < arr.size(); j++){
                JSONObject pJson = arr.getJSONObject(j);
                entity.setPath(pJson.getString("path"));
                entity.setXzb(pJson.getString("X"));
                entity.setYzb(pJson.getString("Y"));
            }
            if(doorsMsMapper.getCountById(json.getString("id")) == 0){
                doorsMsMapper.addMsDoors(entity);
            }
        }
        return null;
    }
}















