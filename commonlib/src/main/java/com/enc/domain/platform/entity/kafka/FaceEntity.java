package com.enc.domain.platform.entity.kafka;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author: BOND
 * @description:
 * @create: 2019-04-20 13:28
 */
@Data
public class FaceEntity {
    private int id;
    private String faceId;
    private String act;
    private String datetime;
    private String scoreRecg;
    private String username;
    private String age;
    private String sex;
    private String identityNum;
    private String phone;

    // @Column(length = 16777215)
    private String image;
    // @Column(length = 16777215)
    private String bgImage;
    // @Column(length = 16777215)
    private String sampleImage;

    private String mac;
    private String ip;
    private String createDate;

}