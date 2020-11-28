package com.enc.controller.common;

import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.service.common.DictionariesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-21 20:47
 */
@RestController
@Api(value = "字典表")
@RequestMapping("/api/")
public class DictionariesController {

    @Autowired
    DictionariesService dictionariesService;

    @ApiOperation(value="获取字典表类型", notes="获取字典表类型", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/dictionary-type", method = RequestMethod.GET)
    public ResponseResult dictionaryTypeList(){
        return ResponseResult.success(dictionariesService.getDictionaryTypeList());
    }

    @ApiOperation(value="获取字典表类型获取状态列表", notes="型获取状态列表", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/dictionary", method = RequestMethod.GET)
    public ResponseResult dictionaryByTypeList(@RequestParam String type){
        return ResponseResult.success(dictionariesService.getDictionaryByTypeList(type));
    }


}