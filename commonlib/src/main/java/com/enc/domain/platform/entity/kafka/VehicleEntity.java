package com.enc.domain.platform.entity.kafka;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-20 13:28
 */
@Data
public class VehicleEntity {

    private int id;
    private String carLogo;
    private String startTime;
    private String carPlate;
    private String color;
    private String parkId;
    private String cameraId;
    private String type;
    // @Column(length = 16777215)
    private String picture;
    private String createDate;

}