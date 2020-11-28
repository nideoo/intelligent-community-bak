package com.enc.domain.access.entity;

import lombok.Data;

import java.util.Date;

/***
 * 随行人员实体
 */
@Data
public class EntourageEntity {
    private String name;
    private Integer sex;
    private Integer relation;
    private Integer nation;
    private String idNumber;
    private Date birthday;
    private Integer inoculationCard;
    private Integer learnStage;
    private String schoolName;
    private Long personId;
}
