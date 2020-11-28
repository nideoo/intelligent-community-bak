package com.enc.domain.access.entity;

import lombok.Data;

/**
 * @author zk-bond
 * @version 1.0
 * @describe
 * @date 2020/11/28 12:42
 */
@Data
public class QueryPersonsEntity {

    private int personId;
    private int areaId;
    private int roomId;
    private String personName;
    private String socialType;
    private String socialNumber;
    private String landlordRelative;
    private String photo;
    private String phone;
    private String regTime;

}
