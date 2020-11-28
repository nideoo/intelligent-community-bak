package com.enc.controller.statistics;

import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.PageInfoVo;
import com.enc.domain.ResponseResult;
import com.enc.domain.platform.vo.statistics.RoomListVo;
import com.enc.service.statistics.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-14 11:04
 */
@Slf4j
@RestController
@Api(value = "统计分析-房屋数据")
@RequestMapping("/api/")
public class RoomContoller {

    @Autowired
    RoomService roomService;

    @ApiOperation(value="获房屋列表", notes="{[address 地址][room_code 房屋编号][room_level 房屋等级][room_level_name 房屋等级中文][police_station  警务室][id][house_type  房屋户型][neighborhood 居委会][room 门牌][certificate_number 房屋产证号][landlord_name 屋主][room_type 房屋使用类型][room_type_name 房屋使用类型中文]}", httpMethod="POST")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/room-list", method = RequestMethod.POST)
    public ResponseResult roomList(@RequestBody RoomListVo vo){
        return ResponseResult.success(roomService.getRoomList(vo));
    }

    @ApiOperation(value="初始化select控件", notes="{[type:[jdxq-初始化街道、小区]、[ld-初始化楼栋]]}", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/init-select", method = RequestMethod.GET)
    public ResponseResult initSelect(@RequestParam(value="id",required=false) String id,@RequestParam String type){
        return ResponseResult.success(roomService.initSelect(id,type));
    }

    @ApiOperation(value="房屋详情", notes="", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/room-info", method = RequestMethod.GET)
    public ResponseResult roomInfo(@RequestParam String id){
        return ResponseResult.success(roomService.getRoomInfo(id));
    }

}




























