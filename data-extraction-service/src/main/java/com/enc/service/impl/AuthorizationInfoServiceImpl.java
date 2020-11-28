package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.dao.mapper.AuthorizationInfoMapper;
import com.enc.dao.mapper.DoorsMsMapper;
import com.enc.dao.mapper.PersonMapper;
import com.enc.domain.ResponseResult;
import com.enc.domain.access.entity.AuthorizationInfoEntity;
import com.enc.domain.access.entity.DoorsMsEntity;
import com.enc.domain.access.vo.term.AuthorizationTermVo;
import com.enc.domain.access.vo.term.DoorsMsTermVo;
import com.enc.service.AuthorizationInfoService;
import com.enc.service.DoorsMsService;
import com.enc.service.MsTokenService;
import com.enc.utils.http.HttpRequest;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-23 11:15
 */
@Service
public class AuthorizationInfoServiceImpl implements AuthorizationInfoService {

    @Autowired
    AuthorizationInfoMapper authorizationInfoMapper;

    @Autowired
    PersonMapper personMapper;

    @Autowired
    MsTokenService msTokenService;

    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;

    @Override
    public ResponseResult getAuthorizationMsBatch() {
        List<Map<String,Object>> list = personMapper.getPersDisNameList();
        for(Map map : list ){
            AuthorizationTermVo vo = new AuthorizationTermVo();
            vo.setProjectID(PROJECTID);
            vo.setPersonName(map.get("name").toString());
            vo.setToken(msTokenService.getToKen());
            String jsonText = JSON.toJSONString(vo);
            JSONObject tjson = JSONObject.parseObject(jsonText);
            String rStr = HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_AUTHORIZATION_URL, tjson,"utf-8");
            JSONObject rJson = JSONObject.parseObject(rStr);
            JSONObject autJson = rJson.getJSONObject("authorizationInfo");
            JSONArray perJsonArr = autJson.getJSONArray("personInfo");
            for(int i = 0; i < perJsonArr.size(); i ++){
                JSONObject pJson = perJsonArr.getJSONObject(i);
                JSONArray dJsonArr = pJson.getJSONArray("doorInfos");
                for(int j = 0; j < dJsonArr.size(); j++){
                    JSONObject dJson = dJsonArr.getJSONObject(j);
                    JSONArray dpArr = dJson.getJSONArray("doorPath");
                    for(int k = 0; k < dpArr.size(); k++){
                        JSONObject dpJson = dpArr.getJSONObject(k);
                        AuthorizationInfoEntity entity = new AuthorizationInfoEntity();
                        entity.setPersonID(pJson.getInteger("personID"));
                        entity.setIdNumber(pJson.getString("idNumber"));
                        entity.setUserPhone(pJson.getString("userPhone"));
                        // entity.setOpenData(pJson.getString("openData"));
                        entity.setDoorID(dJson.getInteger("doorID"));
                        entity.setDoorTyp(dJson.getString("doorTyp"));
                        entity.setDoorName(dJson.getString("doorName"));
                        entity.setConnectionKey(dJson.getString("connectionKey"));
                        entity.setKeyID(dJson.getString("keyID"));
                        entity.setStatus(dJson.getString("status"));
                        entity.setEndTime(dJson.getString("endTime"));
                        entity.setPath(dpJson.getString("path"));
                        entity.setX(dpJson.getString("X"));
                        entity.setY(dpJson.getString("Y"));

                        authorizationInfoMapper.addAuthorization(entity);
                    }
                }
            }
        }
        return null;
    }
}















