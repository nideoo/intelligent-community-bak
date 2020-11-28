package com.enc.domain.access.entity;

import lombok.Data;

/**
 * @description: 楼栋信息 Entity
 * @author: ZhangK-Bond
 * @Date: 2018/12/27
 */
@Data
public class BuildingsEntity {

    /***
     * 楼栋ID
     */
    private int id;
    /***
     * 楼栋名称
     */
    private String name;
    /***
     * 所属区域组ID
     */
    private int areaGroupId;
    /***
     * 楼栋类别
     */
    private String buildingType;
    /***
     * 当前楼栋的人数
     */
    private int personCount;
    /***
     * 楼栋地址
     */
    private String address;
    /***
     * 总层数
     */
    private int totalFloor;
    /***
     * 是否需要统计楼栋人数
     */
    private int isStatistics;
    /***
     *
     */
    private String houseCode;
    /***
     * 房东人员ID
     */
    private int landlordId1;
    /***
     * 二房东人员ID
     */
    private int landlordId2;


}
