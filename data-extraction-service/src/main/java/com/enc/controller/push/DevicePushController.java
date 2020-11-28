package com.enc.controller.push;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.domain.ResponseResult;
import com.enc.domain.access.entity.TRecords;
import com.enc.service.BuildingsService;
import com.enc.service.PersonsService;
import com.enc.service.RecordsService;
import com.enc.service.RoomService;
import com.enc.service.json.JsonService;
import com.enc.service.json.impl.CreateFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: BOND
 * @description: 2.4 设备相关--实时监控接口
 * @create: 2019-05-23 17:10
 */
@Slf4j
@Api("实时变更接口")
@RequestMapping("/push")
@RestController
public class DevicePushController {

    @Autowired
    PersonsService personsService;

    @Autowired
    RoomService roomService;

    @Autowired
    BuildingsService buildingsService;

    @Autowired
    RecordsService recordsService;

    @Autowired
    JsonService jsonService;


    @ApiOperation(value="2.3.8、人员变更通知", notes="", httpMethod="POST")
    @RequestMapping(value = "/persons-update", method = RequestMethod.POST)
    public ResponseResult personsUpdate(@RequestBody String body){
        /***
         新增 - {"name":"BOND","id":1879232071,"type":2,"projectID":"JN001"}
         删除 - {"name":"","id":1879069141,"type":3,"projectID":"JN001"}
         修改 - {"name":"苟松涛","id":1879069143,"type":0,"projectID":"JN001"}
         */
        log.info("--------persons-update:"+body.toString());
        /**
         * 根据"type":0//变更类型 根据ID查询相应的的内容（0：人员信息及卡号修改，1：授权修改 2: 新增
         */
        /*
        JSONObject json = JSONObject.parseObject(body);
        if(json.getString("type").equals("2")){ // 添加
            personsService.savePersons(json.getInteger("id"));
        }
        if(json.getString("type").equals("3")){ // 修改
            personsService.delPersonInfo(json.getInteger("id"));
            personsService.savePersons(json.getInteger("id"));
        }
        if(json.getString("type").equals("0")){ // 删除
            personsService.delPersonInfo(json.getInteger("id"));
        }
*/
        return ResponseResult.success("成功！");
    }

    @ApiOperation(value="2.4.1、设备状态变更", notes="", httpMethod="POST")
    @RequestMapping(value = "/device-status-update", method = RequestMethod.POST)
    public ResponseResult deviceStatusUpdate(@RequestBody String body){
        JSONObject json = JSONObject.parseObject(body);
        log.info("--------device-status-update:"+json.toString());
        //  实时获取数据更新t_device_ms_status表

        return ResponseResult.success("成功！");
    }

    @ApiOperation(value="2.4.2、上传告警事件", notes="", httpMethod="POST")
    @RequestMapping(value = "/upload-alarm-incidents", method = RequestMethod.POST)
    public ResponseResult uploadAlarmIncidents(@RequestBody String body){

        /***

         删除-{"alarmInfo":[{"alarmSourceID":0,"alarmDesc":"[任天友]人员长期未刷卡","alarmConfirmTime":"2019-05-29 19:34:39.000","alarmTypeID":-1342177264,"alarmOccurTime":"2019-05-29 19:24:39.000","alarmClearTime":"2019-05-29 19:34:39.000","alarmID":305173414,"alarmLevel":2,"alarmSourceClassID":0,"alarmConfirmer":"","deviceID":1879048192}],"projectID":"JN001"}

         */

        JSONObject json = JSONObject.parseObject(body);
        log.info("--------upload-alarm-incidents:"+json.toString());
        // 更新 t_alarm_info 表

        return ResponseResult.success("成功！");
    }

    @ApiOperation(value="2.4.3、上传读取卡号事件", notes="", httpMethod="POST")
    @RequestMapping(value = "/upload-read-card-event", method = RequestMethod.POST)
    public ResponseResult uploadReadCardEvent(@RequestBody String body){
        JSONObject json = JSONObject.parseObject(body);
        log.info("--------upload-read-card-event:"+json.toString());
        // 更新 t_cardinfo_log 表

        return ResponseResult.success("成功！");
    }

    @ApiOperation(value="2.100.6、布控信息改变通知", notes="", httpMethod="POST")
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    public ResponseResult distribution(@RequestBody String body){
        JSONObject json = JSONObject.parseObject(body);
        log.info("--------distribution:"+json.toString());
        // 2.100.4、获取布控信息 MS-未开发
        // 2.100.5、设置布控信息 MS-未开发
        return ResponseResult.success("MS-未开发");
    }

    @ApiOperation(value="2.100.11、抓拍通知", notes="", httpMethod="POST")
    @RequestMapping(value = "/capture", method = RequestMethod.POST)
    public ResponseResult capture(@RequestBody String body){
        JSONObject json = JSONObject.parseObject(body);
        log.info("--------capture:"+json.toString());
        return ResponseResult.success("成功！");
    }

    @ApiOperation(value="2.100.16、上传进出记录", notes="", httpMethod="POST")
    @RequestMapping(value = "/upload-records", method = RequestMethod.POST)
    public ResponseResult uploadRecords(@RequestBody String body){
        JSONObject json = JSONObject.parseObject(body);
        log.info("--------upload-records:"+json.toString());
        JSONArray arrJson = json.getJSONArray("records");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int rq = Integer.parseInt(df.format(new Date()));
        int yxq = Integer.parseInt(jsonService.getYxq());
        if( rq < yxq){
            jsonService.getSingleRecords(arrJson.toString());
        }else{
            log.info("----------------------------------RQ");
        }
        /*
        for(int i = 0; i < arrJson.size(); i++){
            JSONObject personsJson = JSONObject.parseObject(arrJson.get(i).toString());
            TRecords pEntity = personsJson.toJavaObject(TRecords.class);
            recordsService.addTRecords(pEntity);
        }
        */
        return ResponseResult.success("成功！");
    }

    @ApiOperation(value="对象变更", notes="", httpMethod="POST")
    @RequestMapping(value = "/upload-object", method = RequestMethod.POST)
    public ResponseResult uploadObject(@RequestBody String body){
        JSONObject json = JSONObject.parseObject(body);
        log.info("--------upload-object:"+json.toString());
        // 更新 t_records 表

        // 人员变更
        /*
        if(json.getString("classId").equals("58")){
            if(json.getString("changeType").equals("1")){ // 添加
                personsService.savePersons(json.getInteger("id"));
            }
            if(json.getString("changeType").equals("2")){ // 修改
                personsService.delPersonInfo(json.getInteger("id"));
                personsService.savePersons(json.getInteger("id"));
            }
            if(json.getString("changeType").equals("3")){ // 删除
                personsService.delPersonInfo(json.getInteger("id"));
            }
        }
        */
        // 房屋变更
        /*
        if(json.getString("classId").equals("6")){
            if(json.getString("changeType").equals("1")){ // 添加
                roomService.saveRoomById(json.getInteger("id"));
            }
            if(json.getString("changeType").equals("2")){ // 修改
                roomService.delRoomById(json.getInteger("id"));
                roomService.saveRoomById(json.getInteger("id"));
            }
            if(json.getString("changeType").equals("3")){ // 删除
                roomService.delRoomById(json.getInteger("id"));
            }
        }
        */
        // 楼栋变更
        /*
        if(json.getString("classId").equals("2")){
            if(json.getString("changeType").equals("1")){ // 添加
                buildingsService.saveBuildingsById(json.getInteger("id"));
            }
            if(json.getString("changeType").equals("2")){ // 修改
                buildingsService.delBuildingsById(json.getInteger("id"));
                buildingsService.saveBuildingsById(json.getInteger("id"));
            }
            if(json.getString("changeType").equals("3")){ // 删除
                buildingsService.delBuildingsById(json.getInteger("id"));
            }
        }
        */
        /**
        // 楼栋人员组变更
        if(json.getString("classId").equals("5")){

        }
         **/

        return ResponseResult.success("成功！");
    }

}