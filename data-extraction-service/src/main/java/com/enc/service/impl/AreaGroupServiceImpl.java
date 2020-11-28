package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.dao.mapper.AreaGroupMapper;
import com.enc.domain.ResponseResult;
import com.enc.domain.access.entity.AreaGroupEntity;
import com.enc.domain.access.vo.term.AreaGroupTermVo;
import com.enc.service.AreaGroupService;
import com.enc.service.MsTokenService;
import com.enc.utils.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-14 17:24
 */
@Service
public class AreaGroupServiceImpl implements AreaGroupService {

    @Autowired
    MsTokenService msTokenService;

    @Autowired
    AreaGroupMapper areaGroupMapper;

    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;

    @Override
    public ResponseResult getAreaGroupBatch() {
        AreaGroupTermVo vo = new AreaGroupTermVo();
        int index = 1;
        vo.setProjectID(PROJECTID);
        vo.setIndex(index);
        vo.setCount(10);
        vo.setParentID("1879053997");
        List<String> list = streetId(index,vo);
        for(String str : list){
            if(!str.equals("1879053997")){
                vo.setParentID(str);
                streetId(index,vo);
            }
        }
        return null;
    }

    private List<String> streetId(int index,AreaGroupTermVo vo){
        String token = msTokenService.getToKen();
        vo.setToken(token);
        List<String> rList = new ArrayList<String>();
        for (int i = index; ; i = vo.getIndex() + vo.getCount()) {
            vo.setIndex(i);
            String jsonText = JSON.toJSONString(vo);
            JSONObject tjson = JSONObject.parseObject(jsonText);
            String rStr = HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_AREA_GROUP_URL, tjson,"utf-8");
            JSONObject rJson = JSONObject.parseObject(rStr);
            JSONArray jsonArr = rJson.getJSONArray("areaGroups");
            if(jsonArr != null){
                if(jsonArr.size() != 0){
                    for(int j = 0; j < jsonArr.size(); j++) {
                        JSONObject json = JSONObject.parseObject(jsonArr.get(j).toString());
                        AreaGroupEntity entity = new AreaGroupEntity();
                        entity.setId(json.getString("id"));
                        entity.setName(json.getString("name"));
                        entity.setParentId(json.getString("parentId"));
                        entity.setAreaGroupType(json.getString("areaGroupType"));
                        entity.setCommunity(json.getString("community"));
                        entity.setAddress(json.getString("address"));
                        entity.setResponsiblePolice(json.getString("responsiblePolice"));
                        entity.setGridman(json.getString("gridman"));
                        entity.setPmCompany(json.getString("pmCompany"));
                        entity.setPmPhone(json.getString("pmPhone"));
                        entity.setPmPerson(json.getString("pmPerson"));
                        entity.setPmMobile(json.getString("pmMobile"));
                        rList.add(json.getString("id"));
                        areaGroupMapper.addAreaGroupData(entity);
                    }
                }else{
                    break;
                }
            }
        }
        return rList;
    }
}