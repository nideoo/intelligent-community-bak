package com.enc.service.homePage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.domain.platform.vo.homePage.HumanCarDetailsVo;

import java.util.List;
import java.util.Map;

public interface HomePageService {

    /***
     * 人口统计
     * @return
     */
    Map<String,Object> getPersonCou();

    /***
     * 实有房屋Sql
     * @return
     */
    String getHousingCou();

    /***
     * 人口结构分析
     * @return
     */
    List<Map<String,Object>> getSexRatioCou();

    /***
     * 获取人车详情
     * @param vo
     * @return
     */
    JSONObject getHumanCarDetails(HumanCarDetailsVo vo);

    /***
     * 人车默认前3条
     * @return
     */
    JSONArray webSockTop3();

    /***
     * 获取设备汇总
     * @return
     */
    List<Map<String,Object>> getDeviceCou();

    /***
     * 获取车辆汇总
     * @return
     */
    List<Map<String,Object>> getVehicleCou();

    /***
     * 人口刘入地
     * @return
     */
    List<Map<String,Object>> getPopuInflowCou();

    /***
     * 房屋结构统计
     * @return
     */
    List<Map<String,Object>> getHousingStructureCou();

    /***
     * 获取重点关注人员汇总
     * @return
     */
    List<Map<String,Object>> getAttentionCou();

}
