package com.enc.controller.statistics;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.domain.platform.vo.areaGroup.AreaGroupListVo;
import com.enc.domain.platform.vo.areaGroup.AreaGroupUpdateVo;
import com.enc.domain.platform.vo.statistics.PersonListVo;
import com.enc.service.statistics.PersonService;
import com.enc.service.system.AreaGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-14 11:04
 */
@Slf4j
@RestController
@Api(value = "统计分析-人员数据")
@RequestMapping("/api/")
public class PersonContoller {

    @Autowired
    PersonService personService;

    @ApiOperation(value="初始化人流入地select", notes="", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/initSel-popflow", method = RequestMethod.GET)
    public ResponseResult initSelPopulInflow(){
        JSONArray arr = new JSONArray();
        JSONObject obj1 = new JSONObject();
        obj1.put("name","西藏");
        obj1.put("value","54");
        arr.add(obj1);
        JSONObject obj2 = new JSONObject();
        obj2.put("name","新疆");
        obj2.put("value","65");
        arr.add(obj2);
        JSONObject obj3 = new JSONObject();
        obj3.put("name","四川藏区");
        obj3.put("value","5133");
        arr.add(obj3);
        /*
        JSONObject obj4 = new JSONObject();
        obj4.put("name","");
        obj4.put("value","");
        arr.add(obj4);
        JSONObject obj5 = new JSONObject();
        obj5.put("name","");
        obj5.put("value","");
        arr.add(obj5);
        */
        return ResponseResult.success(arr);
    }

    @ApiOperation(value="获人员列表", notes="", httpMethod="POST")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/person-list", method = RequestMethod.POST)
    public ResponseResult areaGroupList(@RequestBody PersonListVo vo){
        if(vo.getPopulInflow().length() > 2){
            vo.setPopulInflow1(vo.getPopulInflow());
            vo.setPopulInflow("");
        }
        return ResponseResult.success(personService.getPersonList(vo));
    }

}




























