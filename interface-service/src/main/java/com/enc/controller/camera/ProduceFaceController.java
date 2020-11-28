package com.enc.controller.camera;

import com.alibaba.fastjson.JSONObject;
import com.enc.domain.ResponseResult;
import com.enc.service.camera.FaceCameraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-16 16:43
 */
@Api("接嘉陵区公安局门口人脸相机数据")
@Slf4j
@RestController
@RequestMapping(value = "/camera-receive")
public class ProduceFaceController {

    @Autowired
    FaceCameraService faceCameraService;

    @ApiOperation("接收相机数据")
    @RequestMapping(value = "/face", method = RequestMethod.POST)
    public ResponseResult acceptFace(@RequestParam Map<String, Object> mapObject) {
        JSONObject fJson = JSONObject.parseObject((mapObject.toString().replace("{accessRecord=","").replace("=}","")).toString());
        faceCameraService.faceDataSaveSend(fJson);
        return ResponseResult.success("face");
    }
}














