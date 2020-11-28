package com.enc.domain.access.vo;

import lombok.Data;

@Data
public class CardInfoVo {

    /**
     * 项目编号(必填)
     */
    private String projectID;

    /***
     * 人员ID
     */
    private String id;

    /***
     *
     */
    private String token;

}
