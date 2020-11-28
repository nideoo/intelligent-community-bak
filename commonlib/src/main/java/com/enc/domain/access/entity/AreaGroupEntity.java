package com.enc.domain.access.entity;

import lombok.Data;

/**
 * @description: 获取区域组Entity
 * @author: ZhangK-Bond
 * @Date: 2018/12/27
 */
@Data
public class AreaGroupEntity {

    /***
     * 区域组ID
     */
    private String id;
    /***
     * 区域组名称
     */
    private String name;
    /***
     * 父区域组ID--如果是第一层则为零
     */
    private String parentId;
    /***
     * 区域组类别
     */
    private String areaGroupType;
    /***
     * 所属社区
     */
    private String community;
    /***
     * 详细地址
     */
    private String address;
    /***
     * 责任民警
     */
    private String responsiblePolice;
    /***
     * 网格员
     */
    private String gridman;
    /***
     * 物管公司
     */
    private String pmCompany;
    /***
     * 电话
     */
    private String pmPhone;
    /***
     * 责任人
     */
    private String pmPerson;
    /***
     * 手机
     */
    private String pmMobile;


}
