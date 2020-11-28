package com.enc.constants;

import java.util.HashMap;
import java.util.Map;

public class IntelligentCommunityStatic {
    /**
     * 通用代码
     * **/
    public static final Integer SUCCESS_CODE = 0;
    public static final String SUCCESS_MESSAGE = "成功";
    public static final String SUCCESS_MESSAGE_ENG = "success";

    public static final Integer FAIL_CODE = -1;
    public static final String  FAIL_MESSAGE = "失败";
    public static final String  FAIL_MESSAGE_ENG = "failed";

    public static final String  VERSION = "v1";

    //重点关注人员标签 数据库中的值为1、2、3、、4、5 分别对应 重性精神病人、涉毒人员、刑释解教人员、涉稳人员、独居老人
    public static final String SPIRIT_PEOPLE = "1";
    public static final String DRUG_PEOPLE = "2";
    public static final String PRISON_PEOPLE = "3";
    public static final String STAB_PEOPLE = "4";
    public static final String OLDALONE_PEOPLE = "5";

    public static Map<String, String> pointTypeMap = new HashMap<String, String>();
    static {
        pointTypeMap.put(SPIRIT_PEOPLE, "重性精神病人");
        pointTypeMap.put(DRUG_PEOPLE, "涉毒人员");
        pointTypeMap.put(PRISON_PEOPLE, "刑释解教人员");
        pointTypeMap.put(STAB_PEOPLE, "涉稳人员");
        pointTypeMap.put(OLDALONE_PEOPLE, "独居老人");
    }


    //对象类型
    public static final int OBJECT_TYPE_SERVER = 0;  //服务器
    public static final int OBJECT_TYPE_AREA_GROUP = 1;  //区域组
    public static final int OBJECT_TYPE_BUILD = 2;  //楼栋
    public static final int OBJECT_TYPE_AREA = 3;  //区域
    public static final int OBJECT_TYPE_COMMON_PEOPLE = 4; //普通人员组
    public static final int OBJECT_TYPE_BUILD_PEOPLE = 5;  //楼栋人员组
    public static final int OBJECT_TYPE_ROOM = 6;   //房间
    public static final int OBJECT_TYPE_GUEST_GROUP = 7;   //访客人员组
    public static final int OBJECT_TYPE_PERSON = 58;  //person
    public static final int OBJECT_TYPE_GUEST = 502;  //guest

    public static Map<Integer, String> objectTypeMap = new HashMap<Integer, String>();
    static {
        objectTypeMap.put(OBJECT_TYPE_SERVER, "服务器");
        objectTypeMap.put(OBJECT_TYPE_AREA_GROUP, "区域组");
        objectTypeMap.put(OBJECT_TYPE_BUILD, "楼栋");
        objectTypeMap.put(OBJECT_TYPE_AREA, "区域");
        objectTypeMap.put(OBJECT_TYPE_COMMON_PEOPLE, "普通人员组");
        objectTypeMap.put(OBJECT_TYPE_BUILD_PEOPLE, "楼栋人员组");
        objectTypeMap.put(OBJECT_TYPE_ROOM, "房间");
        objectTypeMap.put(OBJECT_TYPE_GUEST_GROUP, "访客人员组");
        objectTypeMap.put(OBJECT_TYPE_PERSON, "人");
        objectTypeMap.put(OBJECT_TYPE_GUEST, "访客");
    }


    //2.2、身份验证
    public static final String GET_TOKEN_URL = "/api/v1/Token/GetToken";

    //2.100.1、获取人员数据
    public static final String GET_PERSONS_URL = "/api/v1/NanChong/GetPersons";

    //2.100.2、获取人员卡信息
    public static final String GET_CARD_INFO_URL = "/api/v1/NanChong/GetCardInfo";

    //2.100.3、获取房屋数据
    public static final String GET_ROOMS_URL="/api/v1/NanChong/GetRooms";

    //2.100.4、获取布控信息
    public static final String GET_DISTR_CONTROL_URL="/api/v1/NanChong/GetDistributionControl";

    //2.100.5、设置布控信息
    public static final String SET_DISTR_CONTROL_URL="/api/v1/NanChong/SetDistributionControl";

    //2.100.7、获取楼栋信息
    public static final String GET_BUILDINGS_URL="/api/v1/NanChong/GetBuildings";

    //2.100.8、获取区域信息
    public static final String GET_AREA_URL="/api/v1/NanChong/GetArea";

    //2.100.9、获取区域组信息
    public static final String GET_AREA_GROUP_URL="/api/v1/NanChong/GetAreaGroup";

    //2.100.10、获取抓拍信息
    public static final String GET_CAPTURE_URL="/api/v1/NanChong/GetCapture";

    //2.3.1、主控信息（获取设备）
    public static final String GET_DEVICE_MS_URL="/api/v1/Device/GetDevices";

    //2.3.2、根据项目获取门信息（获取门）
    public static final String GET_DOORS_MS_URL="/api/v1/Device/GetDoors";

    //2.3.2、根据项目获取门信息（获取门）
    public static final String GET_AUTHORIZATION_URL="/api/v1/Person/GetAuthorizationByName";

    //2.100.17、订阅通知
    public static final String GET_SUBSCRIPTION_URL="/api/v1/NanChong/SubscriptionNotify";




    //2.3.8、人员变更通知
    public static final String PUSH_PERSONS_URL="/push/persons-update";

    //2.4.1、设备状态变更
    public static final String PUSH_DEVICE_STATUS_URL="/push/device-status-update";

    //2.4.2、上传告警事件
    public static final String PUSH_ALARM_INCIDENTS_URL="/push/upload-alarm-incidents";

    //2.4.3、上传读取卡号事件
    public static final String PUSH_READ_CARD_EVENT_URL="/push/upload-read-card-event";

    //2.100.6、布控信息改变通知
    public static final String PUSH_DISTRIBUTION_URL="/push/distribution";

    //2.100.11、抓拍通知
    public static final String PUSH_CAPTURE_URL="/push/capture";

    //2.100.16、上传进出记录
    public static final String PUSH_RECORDS_URL="/push/upload-records";

    //2.100.16、上传进出记录
    public static final String PUSH_OBJECT_URL="/push/upload-object";

    //2.100.18、人员查询
    public static final String QUERY_PERSON_URL="/api/v1/NanChong/QueryPersons";




}




















