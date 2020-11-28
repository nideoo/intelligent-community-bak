package com.enc.controller.homePage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.domain.platform.vo.homePage.HumanCarDetailsVo;
import com.enc.service.homePage.HomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "首页")
@RequestMapping("/api/")
public class HomePageController {

    @Autowired
    HomePageService homePageService;

    @ApiOperation(value="人口统计", notes="人口统计", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/rktj-statistics", method = RequestMethod.GET)
    public ResponseResult demographicStatistics(){
        Map<String,Object> rMap = homePageService.getPersonCou();
        JSONObject rJson = new JSONObject();
        rJson.put("syrk",rMap.get("syrk"));
        rJson.put("czrk",rMap.get("czrk"));
        rJson.put("zzrk",rMap.get("zzrk"));
        return ResponseResult.success(rJson);
    }

    @ApiOperation(value="房屋统计", notes="房屋统计", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/fwtj-statistics", method = RequestMethod.GET)
    public ResponseResult housingStatistics(){
        JSONObject rJson = new JSONObject();
        rJson.put("syfw",homePageService.getHousingCou());
        return ResponseResult.success(rJson);
    }

    @ApiOperation(value="人口结构分析", notes="人口结构分析", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/rkjgfx-statistics", method = RequestMethod.GET)
    public ResponseResult PopulationStructureAnalysis(){
        JSONArray jsonArr = new JSONArray();
        List<Map<String,Object>> list = homePageService.getSexRatioCou();
        int other = 0;
        for(Map map : list){
            JSONObject json = new JSONObject();
            if(map.get("gender") != null){
                if(map.get("gender").toString().equals("")){
                    other = other + Integer.parseInt(map.get("cou").toString());
                }
                if(map.get("gender").toString().equals("0")){
                    json.put("name","女");
                    json.put("value",map.get("cou"));
                    jsonArr.add(json);
                }
                if(map.get("gender").toString().equals("1")){
                    json.put("name","男");
                    json.put("value",map.get("cou"));
                    jsonArr.add(json);
                }
            }else{
                other = other + Integer.parseInt(map.get("cou").toString());
            }
        }
        JSONObject json = new JSONObject();
        json.put("name","其他");
        json.put("value",other);
        jsonArr.add(json);
        return ResponseResult.success(jsonArr);
    }

    @ApiOperation(value="重点关注", notes="重点关注", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/zdgz-statistics", method = RequestMethod.GET)
    public ResponseResult focusOn(){
        JSONArray jsonArr = new JSONArray();
        List<Map<String,Object>> list = homePageService.getAttentionCou();
        for(Map map : list){
            JSONObject json = new JSONObject();
            json.put("name",map.get("name"));
            json.put("value",map.get("cou"));
            json.put("id",map.get("id"));
            jsonArr.add(json);
        }
        return ResponseResult.success(jsonArr);
    }

    @ApiOperation(value="人口流入地", notes="人口流入地", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/rklrd-statistics", method = RequestMethod.GET)
    public ResponseResult populationInflow(){
        JSONArray jsonArr = new JSONArray();
        List<Map<String,Object>> list = homePageService.getPopuInflowCou();
        for(Map map : list){
            if(map.get("t_province_id") != null){
                JSONObject json = new JSONObject();
                if(map.get("t_province_id").toString().equals("54")){
                    json.put("name","西藏");
                    json.put("value",map.get("cou"));
                }
                if(map.get("t_province_id").toString().equals("65")){
                    json.put("name","新疆");
                    json.put("value",map.get("cou"));
                }
                if(map.get("t_province_id").toString().equals("5133")){
                    json.put("name","四川藏区");
                    json.put("value",map.get("cou"));
                }
                jsonArr.add(json);
            }
        }
        return ResponseResult.success(jsonArr);
    }

    @ApiOperation(value="房屋类型结构分析", notes="房屋类型结构分析", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/fwlxjgfx-statistics", method = RequestMethod.GET)
    public ResponseResult analysisHousingTypes(){
        JSONArray jsonArr = new JSONArray();
        List<Map<String,Object>> list = homePageService.getHousingStructureCou();
        for(Map map : list){
            JSONObject json = new JSONObject();
            json.put("name",map.get("l_name"));
            json.put("value",map.get("cou"));
            jsonArr.add(json);
        }
        return ResponseResult.success(jsonArr);
    }

    @ApiOperation(value="设备数量", notes="设备数量", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/sbsl-statistics", method = RequestMethod.GET)
    public ResponseResult quantityEquipment(){
        JSONArray jsonArr = new JSONArray();
        List<Map<String,Object>> list = homePageService.getDeviceCou();
        for(Map map : list){
            JSONObject json = new JSONObject();
            if(map.get("type").toString().equals("rlxj")){
                json.put("name","人脸相机");
            }
            if(map.get("type").toString().equals("clxj")){
                json.put("name","车辆相机");
            }
            if(map.get("type").toString().equals("mj")){
                json.put("name","门禁");
            }
            json.put("value",map.get("cou"));
            jsonArr.add(json);
        }
        return ResponseResult.success(jsonArr);
    }

    @ApiOperation(value="车辆分析", notes="车辆分析", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/clfx-statistics", method = RequestMethod.GET)
    public ResponseResult vehicleAnalysis(){
        JSONArray jsonArr = new JSONArray();
        List<Map<String,Object>> list = homePageService.getVehicleCou();
        for(Map map : list){
            JSONObject json = new JSONObject();
            if(map.get("type").toString().equals("bdcl")){
                json.put("name","本地车辆");
            }
            if(map.get("type").toString().equals("wdcl")){
                json.put("name","外地车辆");
            }
            json.put("value",map.get("cou"));
            jsonArr.add(json);
        }
        /*
        JSONObject json = new JSONObject();
        json.put("type","其他");
        json.put("value",0);
        jsonArr.add(json);
        */
        return ResponseResult.success(jsonArr);
    }

    @ApiOperation(value="获取人车详情", notes="获取人车详情[type:vehicle|face]", httpMethod="POST")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/human-car-details", method = RequestMethod.POST)
    public ResponseResult getHumanCarDetails(@RequestBody HumanCarDetailsVo vo){
        return ResponseResult.success(homePageService.getHumanCarDetails(vo));
    }

    @ApiOperation(value="人车滚动默认前3条", notes="人车滚动默认前3条", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/human-car-top3", method = RequestMethod.GET)
    public ResponseResult humanCarTop(){
        return ResponseResult.success(homePageService.webSockTop3());
    }
}


































