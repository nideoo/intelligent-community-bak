package com.enc.domain.access.entity;

import lombok.Data;

/**
 * @author: BOND
 * @description: 麦斯设备
 * @create: 2019-05-22 16:10
 */
@Data
public class DevicesMsEntity {


    private int id;

    /***
     * 主控型号
     */
    private String deviceTyp;

    /***
     * 设备名称
     */
    private String name;

    /***
     * 设备位置
     */
    private String devicePath;



}