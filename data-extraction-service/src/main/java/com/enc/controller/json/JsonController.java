package com.enc.controller.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.enc.domain.ResponseResult;
import com.enc.service.SubscriptionService;
import com.enc.service.json.JsonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-23 18:12
 */
@Api("JSON 测试")
@RequestMapping("/")
@RestController
public class JsonController {

    @Autowired
    JsonService jsonService;

    @ApiOperation(value="json", notes="", httpMethod="GET")
    @RequestMapping(value = "/set-json", method = RequestMethod.GET)
    public ResponseResult setSubscribe(){
        jsonService.getCommunity();
        return null;
    }

    /**
     * 楼栋
     */
    @ApiOperation(value="json", notes="", httpMethod="GET")
    @RequestMapping(value = "/set-buildings", method = RequestMethod.GET)
    public ResponseResult setBuildings(){
        jsonService.getBuildings();
        return null;
    }

    /**
     * 房屋
     */
    @ApiOperation(value="json", notes="", httpMethod="GET")
    @RequestMapping(value = "/set-room", method = RequestMethod.GET)
    public ResponseResult getRoom(){
        jsonService.getRoom();
        return null;
    }


    /**
     * 人员
     */
    @ApiOperation(value="json", notes="", httpMethod="GET")
    @RequestMapping(value = "/set-person", method = RequestMethod.GET)
    public ResponseResult getPerson(){
        jsonService.getPerson();
        return null;
    }

    /**
     * 人员
     */
    @ApiOperation(value="json", notes="", httpMethod="GET")
    @RequestMapping(value = "/set-records", method = RequestMethod.GET)
    public ResponseResult getRecords(){
        jsonService.getRecords();
        return null;
    }


}













