package com.enc.controller.batch;

import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("批量获取麦斯数据")
@RequestMapping("/")
@RestController
public class BatchAcquisitionController {

    /***
     * 获取 MS TOKEN
     */
    @Autowired
    private MsTokenService msTokenService;

    /***
     * 房屋管理Service
     */
    @Autowired
    private RoomService roomService;

    /***
     * 楼栋管理Service
     */
    @Autowired
    private BuildingsService buildingsService;

    /***
     * 人员管理Service
     */
    @Autowired
    private PersonsService personsService;

    /***
     * 区域组管理Service
     */
    @Autowired
    private AreaGroupService areaGroupService;

    /***
     * 设备管理Service
     */
    @Autowired
    private DevicesMsService devicesMsService;

    /***
     * 门管理 Service
     */
    @Autowired
    private DoorsMsService doorsMsService;

    /***
     * 获取开门权限 Service
     */
    @Autowired
    private AuthorizationInfoService authorizationInfoService;

    @ApiOperation("2.2、身份验证-获取ToKen")
    // @RequestMapping(value = "/get-token",method = RequestMethod.GET)
    public String getToken(){
        return  msTokenService.getToKen();
    }

    @ApiOperation("2.100.1、获取人员数据/2.100.2、获取人员卡信息(t_person,t_detailinfo t_cardinfo t_entourage)")
    @RequestMapping(value ="/get/persons-batch",method = RequestMethod.GET)
    public ResponseResult getPersonsBatch(){
        personsService.savePersonsBatch();
        return ResponseResult.success(IntelligentCommunityStatic.SUCCESS_MESSAGE);
    }

    @ApiOperation(value="2.100.3、获取房屋数据(t_room)", notes="", httpMethod="GET")
    // @RequestMapping(value = "/getRoomDataBatch", method = RequestMethod.GET)
    public ResponseResult getRoomDataBatch(){
        return roomService.getRoomDataBatch();
    }
/*

    @ApiOperation(value="2.100.4、获取布控信息(t_control)", notes="", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/getDistrControlBatch", method = RequestMethod.GET)
    public ResponseResult getDistrControlBatch(){
        return ResponseResult.fail("麦斯未开发");
    }

    @ApiOperation(value="2.100.5、设置布控信息", notes="", httpMethod="GET")
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/setDistrControlBatch", method = RequestMethod.GET)
    public ResponseResult setDistrControlBatch(){
        return ResponseResult.fail("麦斯未开发");
    }
*/
    @ApiOperation(value="2.100.7、获取楼栋信息(t_buildings)", notes="", httpMethod="GET")
    // @RequestMapping(value = "/getBuildingsBatch", method = RequestMethod.GET)
    public ResponseResult getBuildingsBatch(){
        return buildingsService.getBuildingsBatch();
    }


    @ApiOperation(value="2.100.9、获取区域组信息(t_area_group)", notes="", httpMethod="GET")
    // @RequestMapping(value = "/getAreaGroupBatch", method = RequestMethod.GET)
    public ResponseResult getAreaGroupBatch(){
        return areaGroupService.getAreaGroupBatch();
    }

    @ApiOperation(value="2.3.1、主控信息（获取设备）(t_devices_ms)", notes="", httpMethod="GET")
    // @RequestMapping(value = "/getMsDeviceBatch", method = RequestMethod.GET)
    public ResponseResult getMsDeviceBatch(){
        return devicesMsService.getDeviceMsBatch();
    }

    @ApiOperation(value="2.3.2、根据项目获取门信息（获取门）(t_doors_ms)", notes="", httpMethod="GET")
    // @RequestMapping(value = "/getMsDoorsBatch", method = RequestMethod.GET)
    public ResponseResult getMsDoorsBatch(){
        return doorsMsService.getDoorsMsBatch();
    }

    @ApiOperation(value="2.3.3、根据项目+人名获取开门权限(t_authorization_info)", notes="", httpMethod="GET")
    // @RequestMapping(value = "/getAuthorizationBatch", method = RequestMethod.GET)
    public ResponseResult getAuthorizationBatch(){
        return authorizationInfoService.getAuthorizationMsBatch();
    }

    // 2020-11-28
    @ApiOperation("2.100.18、人员查询(t_query_persons)")
    @RequestMapping(value ="/get/query_persons-batch",method = RequestMethod.GET)
    public ResponseResult queryPersonsBatch(){
        personsService.queryPersonsBatch();
        return ResponseResult.success(IntelligentCommunityStatic.SUCCESS_MESSAGE);
    }


}
