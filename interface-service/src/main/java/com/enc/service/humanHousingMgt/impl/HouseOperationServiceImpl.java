package com.enc.service.humanHousingMgt.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.dao.mapper.humanHousingMgt.HouseOperationMapper;
import com.enc.domain.platform.vo.humanHousingMgt.HumanHousingTermVo;
import com.enc.domain.platform.vo.humanHousingMgt.TreeTermVo;
import com.enc.service.humanHousingMgt.HouseOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HouseOperationServiceImpl implements HouseOperationService {

    @Autowired
    HouseOperationMapper houseOperationMapper;

    @Override
    public JSONObject areaGroupTree(String communityName) {
        TreeTermVo vo = new TreeTermVo();
        vo.setParentId("0");
        List<Map<String,Object>> firstList = houseOperationMapper.getAreaGroupTreeSql(vo);
        JSONObject firstJson = new JSONObject();
        for(Map firstMap : firstList){
            firstJson.put("id",firstMap.get("id"));
            firstJson.put("name",firstMap.get("name"));
            firstJson.put("type",1);
            vo.setParentId(firstMap.get("id").toString());
            List<Map<String,Object>> secondaryList = houseOperationMapper.getAreaGroupTreeSql(vo);
            if(secondaryList.size() == 0){continue;}
            JSONArray secondaryArr = new JSONArray();
            for(Map secondaryMap : secondaryList){
                JSONObject secondaryJSON = new JSONObject();
                secondaryJSON.put("id",secondaryMap.get("id"));
                secondaryJSON.put("name",secondaryMap.get("name"));
                secondaryJSON.put("type","2");
                vo.setParentId(secondaryMap.get("id").toString());
                vo.setCommunityName(communityName);
                List<Map<String,Object>> levelThreeList = houseOperationMapper.getAreaGroupTreeSql(vo);
                if(levelThreeList.size() == 0){continue;}
                JSONArray levelThreeArr = new JSONArray();
                for(Map levelThreeMap : levelThreeList) {
                    JSONObject levelThreeJSON = new JSONObject();
                    levelThreeJSON.put("id", levelThreeMap.get("id"));
                    levelThreeJSON.put("name", levelThreeMap.get("name"));
                    levelThreeJSON.put("type", "3");
                    List<Map<String,Object>> levelFourList = houseOperationMapper.getBuildingsTreeSql(levelThreeMap.get("id").toString());
                    if(levelFourList.size() == 0){continue;}
                    JSONArray levelFourArr = new JSONArray();
                    for(Map levelFourMap : levelFourList) {
                        JSONObject levelFourJSON = new JSONObject();
                        levelFourJSON.put("id", levelFourMap.get("id"));
                        levelFourJSON.put("name", levelFourMap.get("name"));
                        levelFourJSON.put("type", "4");
                        levelFourArr.add(levelFourJSON);
                    }
                    levelThreeJSON.put("children",levelFourArr);
                    levelThreeArr.add(levelThreeJSON);
                }
                secondaryJSON.put("children",levelThreeArr);
                secondaryArr.add(secondaryJSON);
            }
            firstJson.put("children",secondaryArr);
        }
        return firstJson;
    }

    @Override
    public JSONObject humanHousingDetails(HumanHousingTermVo vo) {
        JSONObject rJson = JSONObject.parseObject(JSONObject.toJSON(houseOperationMapper.getHouseOperationDetailsSql(vo.getId())).toString());
        JSONArray jsonArr = new JSONArray();
        Map map = houseOperationMapper.getRoomSumByIdSql(vo.getId());
        // 嘉陵区 或 街道
        if(vo.getType().equals("1") || vo.getType().equals("2") ){
            JSONObject rJsonBjs = new JSONObject();
            rJsonBjs.put("type","bjs");
            rJsonBjs.put("name","未确认的报警数");
            rJsonBjs.put("value","0");

            JSONArray jsonArrObj = new JSONArray();
            JSONObject rJsonFwzs = new JSONObject();
            rJsonFwzs.put("name","房屋总数");
            rJsonFwzs.put("value",map.get("r_cou"));
            jsonArrObj.add(rJsonFwzs);
            JSONObject rJsonBjzs = new JSONObject();
            rJsonBjzs.put("name","报警总数");
            rJsonBjzs.put("value","0");
            jsonArrObj.add(rJsonBjzs);
            JSONObject rJsonJmzs = new JSONObject();
            rJsonJmzs.put("name","居民总数");
            rJsonJmzs.put("value",map.get("p_cou"));
            jsonArrObj.add(rJsonJmzs);

            rJsonBjs.put("collect",jsonArrObj);
            jsonArr.add(rJsonBjs);
        }
        if(vo.getType().equals("3")){
            JSONObject rJsonRkzs = new JSONObject();
            rJsonRkzs.put("type","rkzs");
            rJsonRkzs.put("name","人口总数");
            rJsonRkzs.put("value",map.get("p_cou"));
            JSONArray jsonArrRkzs = new JSONArray();

            List<Map<String,Object>> xqList = houseOperationMapper.getAreaTypeCou(vo.getId());

            if(xqList.size() == 2){
                for(Map xqMap : xqList){
                    JSONObject rJsonCzrk = new JSONObject();
                    rJsonCzrk.put("name", xqMap.get("name"));
                    rJsonCzrk.put("value",xqMap.get("cou"));
                    jsonArrRkzs.add(rJsonCzrk);
                }
            }else{
                Map clMap = xqList.get(0);
                if(clMap.get("name").toString().equals("常住人口")){
                    JSONObject rJsonCzrk = new JSONObject();
                    rJsonCzrk.put("name", clMap.get("name"));
                    rJsonCzrk.put("value",clMap.get("cou"));
                    jsonArrRkzs.add(rJsonCzrk);
                }else{
                    JSONObject rJsonCzrk = new JSONObject();
                    rJsonCzrk.put("name", "流动人口");
                    rJsonCzrk.put("value",0);
                    jsonArrRkzs.add(rJsonCzrk);
                }
                if(clMap.get("name").toString().equals("流动人口")){
                    JSONObject rJsonCzrk = new JSONObject();
                    rJsonCzrk.put("name", clMap.get("name"));
                    rJsonCzrk.put("value",clMap.get("cou"));
                    jsonArrRkzs.add(rJsonCzrk);
                }else{
                    JSONObject rJsonCzrk = new JSONObject();
                    rJsonCzrk.put("name", "常住人口");
                    rJsonCzrk.put("value",0);
                    jsonArrRkzs.add(rJsonCzrk);
                }
            }


            JSONObject rJsonZdgz = new JSONObject();
            rJsonZdgz.put("name","重点关注");
            rJsonZdgz.put("value",houseOperationMapper.getXqZdgzCou(vo.getId()));
            jsonArrRkzs.add(rJsonZdgz);

            rJsonRkzs.put("collect",jsonArrRkzs);
            jsonArr.add(rJsonRkzs);

            JSONObject rJsonFwzs = new JSONObject();
            rJsonFwzs.put("type","fwzs");
            rJsonFwzs.put("name","房屋总数");
            rJsonFwzs.put("value",map.get("r_cou"));
            JSONArray jsonArrFwzs = new JSONArray();

            List<Map<String,Object>> roomTypeList = houseOperationMapper.getRoomTypeSumByIdSql(vo.getId());
            for(Map roomTypeMap : roomTypeList){
                JSONObject roomTypeJson = new JSONObject();
                roomTypeJson.put("name",roomTypeMap.get("lx_name"));
                roomTypeJson.put("value",roomTypeMap.get("cou"));
                jsonArrFwzs.add(roomTypeJson);
            }

            jsonArr.add(rJsonFwzs);
        }

        rJson.put("census",jsonArr);

        return rJson;
    }

    @Override
    public JSONObject buildingDetails(String buildingId) {
        int floor = houseOperationMapper.getMaxFloorSql(buildingId);
        List<Map<String,Object>> rList = houseOperationMapper.getRoomSql(buildingId);
        JSONArray buildings = new JSONArray();
        for(int i = 1; i <= floor; i++){
            JSONObject floorJson = new JSONObject();
            floorJson.put("floor",i);
            JSONArray roomJsonArr = new JSONArray();
            for(Map rMap : rList){
                if(Integer.parseInt(rMap.get("floor").toString()) == i){
                    JSONObject roomObj = new JSONObject();
                    roomObj.put("id",rMap.get("id"));
                    roomObj.put("name",rMap.get("name"));
                    // roomObj.put("people",houseOperationMapper.getRoomPersonCouSql(rMap.get("id").toString())); // 房间总共人员
                    Map<String,Object> gzMap = houseOperationMapper.getGzType(rMap.get("id").toString());
                    roomObj.put("gz_type",""); // 重点关注状态: 1-重点关注 2-前科 3- 涉毒
                    if(!gzMap.get("gz_cou").toString().equals("0")){
                        roomObj.put("gz_type","gzry");
                    }
                    if(!gzMap.get("zd_cou").toString().equals("0")){
                        roomObj.put("gz_type","zdry");
                    }
                    roomObj.put("room_type",rMap.get("room_type"));
                    roomJsonArr.add(roomObj);
                }
            }
            floorJson.put("rooms",roomJsonArr);
            buildings.add(floorJson);
        }

        Map map = houseOperationMapper.getRoomSumByIdSql(buildingId);
        JSONObject rJson = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        JSONObject rJsonFwzs = new JSONObject();
        rJsonFwzs.put("type","fwzs");
        rJsonFwzs.put("name","房屋总数");
        rJsonFwzs.put("value",map.get("r_cou"));

        JSONArray jsonArrFwzs1 = new JSONArray();
        JSONObject rJsonZzfw1 = new JSONObject();
        rJsonZzfw1.put("name","居民总数");
        rJsonZzfw1.put("value",map.get("p_cou"));
        jsonArrFwzs1.add(rJsonZzfw1);

        JSONObject rJsonCzfw1 = new JSONObject();
        rJsonCzfw1.put("name","楼层数");
        rJsonCzfw1.put("value",floor);
        jsonArrFwzs1.add(rJsonCzfw1);

        JSONObject rJsonXzfw1 = new JSONObject();
        rJsonXzfw1.put("name","房间数");
        rJsonXzfw1.put("value",map.get("r_cou"));
        jsonArrFwzs1.add(rJsonXzfw1);
        rJsonFwzs.put("collect",jsonArrFwzs1);
        rJson.put("oneFwzs",rJsonFwzs);



        JSONArray jsonArrRkzs = new JSONArray();
        JSONObject rJsonRkzs = new JSONObject();
        /*
        rJsonRkzs.put("type","rkzs");
        rJsonRkzs.put("name","人口总数");
        rJsonRkzs.put("value",map.get("p_cou"));
*/
        JSONObject rJsonrkzs = new JSONObject();
        rJsonrkzs.put("name","人口总数");
        rJsonrkzs.put("value",map.get("p_cou"));
        jsonArrRkzs.add(rJsonrkzs);


        List<Map<String,Object>> ldList = houseOperationMapper.getLdRsCou(buildingId);
        if(ldList.size() > 1){
            for(Map ldMap : ldList){
                JSONObject rJsonCzrk = new JSONObject();
                rJsonCzrk.put("name",ldMap.get("name"));
                rJsonCzrk.put("value",ldMap.get("cou"));
                jsonArrRkzs.add(rJsonCzrk);
            }
        }else{
            Map<String,Object> mapRs = ldList.get(0);
            JSONObject rJsonCzrk = new JSONObject();
            rJsonCzrk.put("name",mapRs.get("name"));
            rJsonCzrk.put("value",mapRs.get("cou"));
            jsonArrRkzs.add(rJsonCzrk);
            if(!mapRs.get("name").toString().equals("常住人口")){
                JSONObject rJsonCzrk1 = new JSONObject();
                rJsonCzrk1.put("name","常住人口");
                rJsonCzrk1.put("value","0");
                jsonArrRkzs.add(rJsonCzrk1);
            }
            if(!mapRs.get("name").toString().equals("流动人口")){
                JSONObject rJsonCzrk2 = new JSONObject();
                rJsonCzrk2.put("name","流动人口");
                rJsonCzrk2.put("value","0");
                jsonArrRkzs.add(rJsonCzrk2);
            }
        }
        JSONObject rJsonZdgz = new JSONObject();
        rJsonZdgz.put("name","重点关注");
        rJsonZdgz.put("value",houseOperationMapper.getZdgzCou(buildingId));
        jsonArrRkzs.add(rJsonZdgz);
        rJsonRkzs.put("collect",jsonArrRkzs);
        rJson.put("twoRktj",rJsonRkzs);

        /**
        JSONArray jsonArrFwzs = new JSONArray();
        List<Map<String,Object>> roomTypeList = houseOperationMapper.getRoomTypeSumByIdSql(buildingId);
        for(Map roomTypeMap : roomTypeList){
            JSONObject rJsonZzfw = new JSONObject();
            rJsonZzfw.put("name",roomTypeMap.get("lx_name"));
            rJsonZzfw.put("value",roomTypeMap.get("cou"));
            jsonArrFwzs.add(rJsonZzfw);
        }
        jsonArr.add(jsonArrFwzs);

        rJson.put("census",jsonArr);
        **/

        List<Map<String,Object>> roomTypeList = houseOperationMapper.getRoomTypeSumByIdSql(buildingId);

        JSONObject rJsonFwzsThee = new JSONObject();
        rJsonFwzsThee.put("type","fwzs");
        rJsonFwzsThee.put("name","房屋总数");
        rJsonFwzsThee.put("value",map.get("r_cou"));
        JSONArray jsonArrLx = new JSONArray();
        for(Map roomTypeMap : roomTypeList){
            JSONObject rJsonChuZhu = new JSONObject();
            rJsonChuZhu.put("name",roomTypeMap.get("lx_name"));
            rJsonChuZhu.put("value",roomTypeMap.get("cou"));
            jsonArrLx.add(rJsonChuZhu);
        }
/*
        JSONObject rJsonZiZhu = new JSONObject();
        rJsonZiZhu.put("name","自住");
        rJsonZiZhu.put("value",floor);
        jsonArrLx.add(rJsonZiZhu);

        JSONObject rJsonXianZhi = new JSONObject();
        rJsonXianZhi.put("name","闲置");
        rJsonXianZhi.put("value",map.get("r_cou"));
        jsonArrLx.add(rJsonXianZhi);
        */

        rJsonFwzsThee.put("collect",jsonArrLx);
        rJson.put("threeFwzs",rJsonFwzsThee);


        rJson.put("buildings",buildings);
        return rJson;
    }

    @Override
    public Map<String, Object> getRoomDetails(String roomId) {
        Map<String,Object> rMap = new HashMap<String,Object>();
        List<Map<String,Object>> list = houseOperationMapper.getRoomDetailsSql(roomId);
        rMap.put("type","1");
        rMap.put("list",list);
        return rMap;
    }

    @Override
    public Map<String, Object> getAreaGroupInfo(String id) {
        return houseOperationMapper.getAreaGroupInfo(id);
    }
}























