package com.enc.service.homePage.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.dao.mapper.history.PedestrianHistoryMapper;
import com.enc.dao.mapper.history.VehicleHistoryMapper;
import com.enc.dao.mapper.homePage.HomePageMapper;
import com.enc.domain.platform.entity.kafka.FaceEntity;
import com.enc.domain.platform.entity.kafka.VehicleEntity;
import com.enc.domain.platform.vo.homePage.HumanCarDetailsVo;
import com.enc.service.homePage.HomePageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-28 12:44
 */
@Slf4j
@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    HomePageMapper homePageMapper;
    @Autowired
    PedestrianHistoryMapper pedestrianHistoryMapper;
    @Autowired
    VehicleHistoryMapper vehicleHistoryMapper;

    @Override
    public Map<String, Object> getPersonCou() {
        return homePageMapper.getPersonCouSql();
    }

    @Override
    public String getHousingCou() {
        return homePageMapper.getHousingCouSql();
    }

    @Override
    public List<Map<String, Object>> getSexRatioCou() {
        return homePageMapper.getSexRatioCouSql();
    }

    @Override
    public JSONObject getHumanCarDetails(HumanCarDetailsVo vo) {
        JSONObject rjson = null;
        if(vo.getType().equals("face")){
            FaceEntity fEntity  = homePageMapper.getFaceInfoById(vo.getId());
            rjson = JSONObject.parseObject(JSONObject.toJSON(fEntity).toString());
            rjson.put("showType",vo.getType());
        }
        if(vo.getType().equals("vehicle")){
            VehicleEntity vEntity = homePageMapper.getVehicleInfoById(vo.getId());
            rjson = JSONObject.parseObject(JSONObject.toJSON(vEntity).toString());
            rjson.put("showType",vo.getType());
        }
        return rjson;
    }

    @Override
    public JSONArray webSockTop3() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        JSONArray arr = new JSONArray();
        List<Map<String,Object>> pList = pedestrianHistoryMapper.getHisPedestrianTopSql(sdf.format(new Date()));
        List<Map<String,Object>> vList = vehicleHistoryMapper.getHisVehicleTopSql(sdf.format(new Date()));
        for(Map map : pList){
            arr.add(map);
        }
        for(Map map : vList){
            arr.add(map);
        }
        return arr;
    }

    @Override
    public List<Map<String, Object>> getDeviceCou() {
        return homePageMapper.getDeviceCou();
    }

    @Override
    public List<Map<String, Object>> getVehicleCou() {
        return homePageMapper.getVehicleCou();
    }

    @Override
    public List<Map<String, Object>> getPopuInflowCou() {
        return homePageMapper.getPopuInflowCou();
    }

    @Override
    public List<Map<String, Object>> getHousingStructureCou() {
        return homePageMapper.getHousingStructureCou();
    }

    @Override
    public List<Map<String, Object>> getAttentionCou() {
        return homePageMapper.getAttentionCou();
    }
}















