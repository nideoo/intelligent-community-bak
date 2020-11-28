package com.enc.dao.mapper.homePage;

import com.enc.domain.platform.entity.kafka.FaceEntity;
import com.enc.domain.platform.entity.kafka.VehicleEntity;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-28 12:48
 */
public interface HomePageMapper {

    /***
     * 人口统计sql
     * @return
     */
    Map<String,Object> getPersonCouSql();

    /***
     * 实有房屋Sql
     * @return
     */
    String getHousingCouSql();

    /***
     * 人口结构分析sql
     * @return
     */
    List<Map<String,Object>> getSexRatioCouSql();

    FaceEntity getFaceInfoById(String id);

    VehicleEntity getVehicleInfoById(String id);

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


















