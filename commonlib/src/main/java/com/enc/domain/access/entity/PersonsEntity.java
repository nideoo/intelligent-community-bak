package com.enc.domain.access.entity;

import lombok.Data;

/****
 * 人员表
 */
@Data
public class PersonsEntity {
    //人员ID
    private Long id;
    //人员姓名
    private String name;
    //证件号
    private String idNumber;
    //手机号
    private String phone;
    //关注等级
    private Integer level;
    //父对象ID（房间ID或人员组ID）
    private String parentID;

    private String personId;

    private String gender;

}
