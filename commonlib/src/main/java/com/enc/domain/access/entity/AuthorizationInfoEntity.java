package com.enc.domain.access.entity;

import lombok.Data;

/**
 * @author: BOND
 * @description: 开门权限
 * @create: 2019-05-22 16:10
 */
@Data
public class AuthorizationInfoEntity {

    /***
     * 人员编号
     */
    private int personID;

    /***
     * 门ID
     */
    private int doorID;

    /***
     * 身份证号
     */
    private String idNumber;

    /***
     * 人员手机号
     */
    private String userPhone;

    /***
     * 人员开门证书
     */
    private String openData;

    /***
     * 门型号
     */
    private String doorTyp;

    /***
     * 门名称
     */
    private String doorName;

    /***
     * 门位置
     */
    private String path;

    /***
     *
     */
    private String X;

    /***
     *
     */
    private String Y;

    /***
     * 门通讯密钥
     */
    private String connectionKey;

    /***
     * 蓝牙密钥
     */
    private String keyID;

    /***
     * 门状态 0离线 1在线
     */
    private String status;

    /***
     * 人员访问门的授权有效期截止时间
     */
    private String endTime;

}