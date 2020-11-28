package com.enc.domain.access.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/***
 * 人员详细信息表
 */
@Data
public class DetailInfoEntity {
    private String otherName;   //曾用名
    private Integer sex;        //性别
    private Integer idCardType;
    private String birthday;
    private String profession;
    private String unit;
    private String nation;
    private String residenceAddress;
    private Integer accountType;
    private String accountAddress;
    private Integer province;
    private Integer educationLevel;
    private Integer maritalStatus;
    private String spouseName;
    private String spousePhone;
    private String spouseIDCardNO;
    private Integer populationType;
    private Integer liveType;
    private Integer certificateType;
    private String religion;
    private Integer payCloseAttention;
    private String alarmDay;
    private Integer level;
    private String remark;
    // @Column(length = 16777215)
    private String photo;
    // @Column(length = 16777215)
    private String realTimePhoto;
    // @Column(length = 16777215)
    private String otherPhoto;
    private String trade;
    private Integer positionalTitles;
    private String employeeID;
    private String unitAddress;
    private String post;
    private String emergencyContact;
    private String emergencyContactPhone;
    private String officePhone;
    private String workPhone;
    private String email;
    private String weChat;
    private String rtx;
    private String unitPoliceStation;
    private String department;
    private Integer unionist;
    private Integer militaryService;
    private Integer residenceType;
    private Integer residenceCause;
    private Date moveInTime;
    private Date moveOutTime;
    private String area;
    private String town;
    private String policeStation;
    private String neighborhood;
    private String street;
    private String basicGridCell;
    private String buildingNO;
    private String roomNO;
    private String unitNO;
    private String roomCode;
    private String landlordName;
    private String landlordIDCard;
    private String landlordPhone;
    private Integer landlordRelative;
    private Integer liabilityStatement;
    private Integer isLessee;
    private Integer isRecurrent;
    private Integer bearStatus;
    private Integer contraceptionStatus;
    private Integer familyPlanCardType;
    private String familyPlanNO;
    private Integer smallChild;
    private Integer disabilityType;
    private Integer residencePermitType;
    private String residencePermitNO;
    private Integer isFirstBid;
    private Date residencePermitCreateDate;
    private Date residencePermitValidate;
    //填表人
    private String fillingName;
    //填表单位
    private String fillingUnit;
    //填表日期
    private Date fillingDate;
    //跟person表关联的字段
    private Long personId;

}
