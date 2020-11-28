package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.dao.mapper.BuildingsMapper;
import com.enc.domain.ResponseResult;
import com.enc.domain.access.entity.BuildingsEntity;
import com.enc.domain.access.vo.term.BuildingsByIdTermVo;
import com.enc.domain.access.vo.term.BuildingsTermVo;
import com.enc.service.BuildingsService;
import com.enc.service.MsTokenService;
import com.enc.utils.http.HttpRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-14 17:10
 */
@Slf4j
@Service
public class BuildingsServiceImpl implements BuildingsService {

    @Autowired
    MsTokenService msTokenService;

    @Autowired
    BuildingsMapper buildingsMapper;

    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;

    @Override
    public ResponseResult getBuildingsBatch() {
        String token = msTokenService.getToKen();
        BuildingsTermVo vo = new BuildingsTermVo();
        int index = 1;
        vo.setProjectID(PROJECTID);
        vo.setIndex(index);
        vo.setCount(10);
        vo.setAreaGroupID(1879053997);
        vo.setToken(token);
        for (int i = index; ; i = vo.getIndex() + vo.getCount()) {
            log.info("-----------------------t_buildings------------:"+i);
            vo.setIndex(i);
            String jsonText = JSON.toJSONString(vo);
            JSONObject tjson = JSONObject.parseObject(jsonText);
            String rStr = HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_BUILDINGS_URL, tjson,"utf-8");
            JSONObject rJson = JSONObject.parseObject(rStr);
            JSONArray jsonArr = rJson.getJSONArray("buildings");
            if(jsonArr != null){
                if(jsonArr.size() != 0){
                    addBuildingsModel(jsonArr);
                }else{
                    break;
                }
            }
        }
        return ResponseResult.success("获取楼栋信息初始化成功！");
    }

    @Override
    public void saveBuildingsById(int id) {
        String token = msTokenService.getToKen();
        BuildingsByIdTermVo vo = new BuildingsByIdTermVo();
        int index = 1;
        vo.setProjectID(PROJECTID);
        vo.setIndex(index);
        vo.setCount(10);
        vo.setId(id);
        vo.setToken(token);
        for (int i = index; ; i = vo.getIndex() + vo.getCount()) {
            vo.setIndex(i);
            String jsonText = JSON.toJSONString(vo);
            JSONObject tjson = JSONObject.parseObject(jsonText);
            String rStr = HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_BUILDINGS_URL, tjson,"utf-8");
            JSONObject rJson = JSONObject.parseObject(rStr);
            JSONArray jsonArr = rJson.getJSONArray("buildings");
            if(jsonArr != null){
                if(jsonArr.size() != 0){
                    addBuildingsModel(jsonArr);
                }else{
                    break;
                }
            }
        }
    }

    private void addBuildingsModel(JSONArray jsonArr){
        for(int j = 0; j < jsonArr.size(); j++) {
            JSONObject json = JSONObject.parseObject(jsonArr.get(j).toString());
            BuildingsEntity entity = new BuildingsEntity();
            entity.setId(Integer.parseInt(json.getString("id")));
            entity.setName(json.getString("name"));
            entity.setAreaGroupId(Integer.parseInt(json.getString("areaGroupId")));
            entity.setBuildingType(json.getString("buildingType"));
            entity.setPersonCount(Integer.parseInt(json.getString("personCount")));
            entity.setAddress(json.getString("address"));
            entity.setTotalFloor(Integer.parseInt(json.getString("totalFloor")));
            entity.setIsStatistics(Integer.parseInt(json.getString("isStatistics")));
            entity.setHouseCode(json.getString("houseCode"));
            entity.setLandlordId1(Integer.parseInt(json.getString("landlordId1")));
            entity.setLandlordId2(Integer.parseInt(json.getString("landlordId2")));
            buildingsMapper.addBuildingsData(entity);
        }
    }

    @Override
    public void delBuildingsById(int id) {
        buildingsMapper.delBuildingsByIdSql(id);
    }
}