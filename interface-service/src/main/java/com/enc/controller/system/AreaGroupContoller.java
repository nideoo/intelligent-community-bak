package com.enc.controller.system;

import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.domain.platform.vo.areaGroup.AreaGroupListVo;
import com.enc.domain.platform.vo.areaGroup.AreaGroupUpdateVo;
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
@Api(value = "系统管理-[区、街道、小区设置]")
@RequestMapping("/api/")
public class AreaGroupContoller {

    @Value("${patch.areaGroup.local-url}")
    private String areaGroupLocalUrl;

    @Value("${patch.areaGroup.data-url}")
    private String areaGroupDataUrl;

    @Autowired
    AreaGroupService areaGroupService;

    @ApiOperation(value="获取区、街道、小区列表", notes="", httpMethod="POST")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/area-group-list", method = RequestMethod.POST)
    public ResponseResult areaGroupList(@RequestBody AreaGroupListVo vo){
        return ResponseResult.success(areaGroupService.getAreaGroupList(vo));
    }

    @ApiOperation("areaGroup 图片上传")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/import-areaGroup-img", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult importAreaGroupImg(@RequestParam("file4") MultipartFile file4, HttpServletRequest request){
        return ResponseResult.success(areaGroupService.pictureHandle(file4,request));
    }

    @ApiOperation(value="修改区、街道、小区", notes="", httpMethod="POST")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/area-group-update", method = RequestMethod.POST)
    public ResponseResult areaGroupUpdate(@RequestBody AreaGroupUpdateVo vo){
        areaGroupService.areaGroupUpdate(vo);
        return ResponseResult.success("修改成功！");
    }

}




























