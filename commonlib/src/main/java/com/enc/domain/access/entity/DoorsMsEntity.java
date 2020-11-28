package com.enc.domain.access.entity;

import lombok.Data;

/**
 * @author: BOND
 * @description: 麦斯门
 * @create: 2019-05-22 16:10
 */
@Data
public class DoorsMsEntity {

    /***
     * 门编号
     */
    private int id;

    /***
     * 门型号
     */
    private String doorTyp;

    /***
     * 门名称
     */
    private String name;

    /***
     * 门状态 0离线 1在线
     */
    private String status;

    /***
     * 门位置
     */
    private String path;

    /***
     *
     */
    private String xzb;

    /***
     *
     */
    private String yzb;


}