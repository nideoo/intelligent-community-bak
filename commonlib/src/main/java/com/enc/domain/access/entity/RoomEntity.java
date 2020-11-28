package com.enc.domain.access.entity;

import lombok.Data;

/***
 * 房屋 Entity
 */
@Data
public class RoomEntity {

    /***
     * 房屋ID
     */
    public String id;
    /***
     * 房屋名称
     */
    public String name;
    /***
     * 所属楼栋ID
     */
    public String buildingId;
    /***
     * 市
     */
    public String city;
    /***
     * 区
     */
    public String area;
    /***
     * 街道办
     */
    public String town;
    /***
     * 警务室
     */
    public String policeStation;
    /***
     * 居委会
     */
    public String neighborhood;
    /***
     * 街道
     */
    public String street;
    /***
     * 号
     */
    public String buildingNo;
    /***
     * 小区
     */
    public String community;
    /***
     * 楼栋
     */
    public String building;
    /***
     * 单元
     */
    public String unit;
    /***
     * 房
     */
    public String roomNo;
    /***
     * 房屋编号
     */
    public String roomCode;
    /***
     * 屋主
     */
    public String landlordName;
    /***
     * 房屋等级
     */
    public String roomLevel;
    /***
     * 房屋使用类型
     */
    public String roomType;
    /***
     * 楼层
     */
    public String floor;
    /***
     * 房屋户型
     */
    public String houseType;
    /***
     * 房屋产证号
     */
    public String certificateNumber;


}
