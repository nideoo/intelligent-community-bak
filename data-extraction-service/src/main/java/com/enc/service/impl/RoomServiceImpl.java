package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.dao.mapper.BuildingsMapper;
import com.enc.dao.mapper.RoomMapper;
import com.enc.domain.ResponseResult;
import com.enc.domain.access.entity.RoomEntity;
import com.enc.domain.access.vo.term.HouseIdTermMsVo;
import com.enc.domain.access.vo.term.HouseTermMsVo;
import com.enc.service.RoomService;
import com.enc.service.MsTokenService;
import com.enc.utils.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ZhangK-Bond
 * @Date: 2018/12/25
 */
@Slf4j
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    MsTokenService msTokenService;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    BuildingsMapper buildingsMapper;

    /*@Autowired
    HouseMapper houseMapper;*/
/*
    @Autowired
    AreaGroupMapper areaGroupMapper;

*/
    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;


    @Override
    public ResponseResult getRoomDataBatch() {

        List<Map<String,Object>> list = buildingsMapper.getbuildingsId();
        for(Map map : list){
            HouseTermMsVo msVo = new HouseTermMsVo();
            int index = 1;
            String token = msTokenService.getToKen();
            msVo.setToken(token);
            msVo.setProjectID(PROJECTID);
            msVo.setCount(10);
            msVo.setIndex(index);
            msVo.setBuildingID(Integer.parseInt(map.get("id").toString()));
            for (int i = index; ; i = msVo.getIndex() + msVo.getCount()) {
                msVo.setIndex(i);
                String jsonText = JSON.toJSONString(msVo);
                JSONObject tjson = JSONObject.parseObject(jsonText);
                String rStr = HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_ROOMS_URL, tjson,"utf-8");
                JSONObject rJson = JSONObject.parseObject(rStr);
                JSONArray rArray = rJson.getJSONArray("rooms");
                msVo.setIndex(i+msVo.getCount()-1);
                if(rArray != null){
                    if(rArray.size() != 0){
                        addRoomEntity(rArray);
                    }else{
                        break;
                    }
                }
            }
        }

        return ResponseResult.success("房屋信息初始化成功！");
    }

    @Override
    public void saveRoomById(int id) {
        HouseIdTermMsVo msVo = new HouseIdTermMsVo();
        int index = 1;
        String token = msTokenService.getToKen();
        msVo.setToken(token);
        msVo.setProjectID(PROJECTID);
        msVo.setCount(10);
        msVo.setIndex(index);
        msVo.setId(id);
        for (int i = index; ; i = msVo.getIndex() + msVo.getCount()) {
            msVo.setIndex(i);
            String jsonText = JSON.toJSONString(msVo);
            JSONObject tjson = JSONObject.parseObject(jsonText);
            String rStr = HttpRequest.sendPost(MAIS_SERVER+IntelligentCommunityStatic.GET_ROOMS_URL, tjson,"utf-8");
            JSONObject rJson = JSONObject.parseObject(rStr);
            JSONArray rArray = rJson.getJSONArray("rooms");
            msVo.setIndex(i+msVo.getCount()-1);
            if(rArray != null){
                if(rArray.size() != 0){
                    addRoomEntity(rArray);
                }else{
                    break;
                }
            }
        }
    }

    private void addRoomEntity(JSONArray rArray){
        for(int i = 0; i < rArray.size(); i++){
            JSONObject json = JSONObject.parseObject(rArray.get(i).toString());
            RoomEntity entity = new RoomEntity();
            entity.setId(json.getString("id"));
            entity.setName(json.getString("name"));
            entity.setBuildingId(json.getString("buildingID"));
            entity.setCity(json.getString("city"));
            entity.setArea(json.getString("area"));
            entity.setTown(json.getString("town"));
            entity.setPoliceStation(json.getString("policeStation"));
            entity.setNeighborhood(json.getString("neighborhood"));
            entity.setStreet(json.getString("street"));
            entity.setBuildingNo(json.getString("buildingNO"));
            entity.setCommunity(json.getString("community"));
            entity.setBuildingNo(json.getString("building"));
            entity.setUnit(json.getString("unit"));
            entity.setRoomNo(json.getString("roomNO"));
            entity.setRoomCode(json.getString("roomCode"));
            entity.setLandlordName(json.getString("landlordName"));
            entity.setRoomLevel(json.getString("roomLevel"));
            entity.setRoomType(json.getString("roomType"));
            entity.setFloor(json.getString("floor"));
            entity.setHouseType(json.getString("houseType"));
            entity.setCertificateNumber(json.getString("certificateNumber"));

            if(roomMapper.getRoomById(entity.getId())== 0){
                roomMapper.addRoom(entity);
            }
        }
    }

    @Override
    public void saveUpadateRoom() {
        List<Map<String,Object>> list = roomMapper.getRoomByIdSumSql();
        for(Map map : list){
            if(map.get("id") != null){
                if(roomMapper.getRoomSumCount(map.get("id").toString()) == 0){
                    roomMapper.addRoomSum(map);
                }else{
                    roomMapper.updateRoomSum(map);
                }
            }else{
                roomMapper.addRoomSum(map);
            }
        }
    }

    @Override
    public void delRoomById(int id) {
        roomMapper.delRoomByIdSql(id);
    }
}










