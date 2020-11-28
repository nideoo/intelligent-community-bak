package com.enc.controller.humanHousingMgt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.domain.platform.vo.humanHousingMgt.HumanHousingTermVo;
import com.enc.service.humanHousingMgt.HouseOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@Api(value = "人房管理")
@RequestMapping("/api/")
public class HouseOperationController {

    @Autowired
    HouseOperationService houseOperationService;

    //获取 Tree - 树结构
    @ApiOperation(value="获取树结构", notes="", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/tree-structure", method = RequestMethod.GET)
    public ResponseResult getTree(@RequestParam(name="communityName",required = false) String communityName){
        JSONArray rarr = new JSONArray();
        rarr.add(houseOperationService.areaGroupTree(communityName));
        return ResponseResult.success(rarr);
    }

    @ApiOperation(value="根据ID、TYPE查询人房详情", notes="[type: 1-区 2-辖区 3-小区 4-楼栋][room_type:1-自主房 2-出租房 3-空闲房][gz_type:gzry-关注人员 zdry-重点人员]", httpMethod="POST")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/getHuman-housing-details", method = RequestMethod.POST)
    public ResponseResult getHumanHousingDetails(@RequestBody HumanHousingTermVo vo){
        JSONObject rJson = null;
        if(vo.getType().equals("4")){
            rJson = houseOperationService.buildingDetails(vo.getId());
        }else{
            rJson = houseOperationService.humanHousingDetails(vo);
        }
        return ResponseResult.success(rJson);
    }

    @ApiOperation(value="根据房屋ID查询人房详情", notes="[name-门号 floor-楼层 personCou-居住人数 roomType-房屋类型 type-1-重点关注 2-前科 3- 涉毒]", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/room-details", method = RequestMethod.GET)
    public ResponseResult getRoomDetails(@RequestParam String roomId){
        return ResponseResult.success(houseOperationService.getRoomDetails(roomId));
    }

    @ApiOperation(value="获取区域组详情", notes="", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/area-group-info", method = RequestMethod.GET)
    public ResponseResult areaGroupInfo(@RequestParam String id){
        return ResponseResult.success(houseOperationService.getAreaGroupInfo(id));
    }


}


















