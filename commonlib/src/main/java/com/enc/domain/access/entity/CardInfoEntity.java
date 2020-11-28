package com.enc.domain.access.entity;

import lombok.Data;

import java.util.Date;

/***
 * 卡号记录表 Entity
 */
@Data
public class CardInfoEntity {
    private String cardNO;
    private Date startTime;
    private Date endTime;
    private Integer cardState;
    private Integer personCardType;
    private Integer cardType;
    private String personId;
}
