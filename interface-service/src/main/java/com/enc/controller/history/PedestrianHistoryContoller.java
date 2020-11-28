package com.enc.controller.history;

import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.domain.platform.vo.history.pedestrian.PedestrianListQueryVo;
import com.enc.service.history.PedestrianHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-21 22:20
 */
@RestController
@Api(value = "历史数据-卡口-过往行人")
@RequestMapping("/api/")
public class PedestrianHistoryContoller {

    @Autowired
    PedestrianHistoryService pedestrianHistoryService;

    @ApiOperation(value="过往行人列表", notes="", httpMethod="POST")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/his-Pedestrian-list", method = RequestMethod.POST)
    public ResponseResult hisPedestrianList(@RequestBody PedestrianListQueryVo vo){
        return ResponseResult.success(pedestrianHistoryService.getHisPedestrianList(vo));
    }

}





