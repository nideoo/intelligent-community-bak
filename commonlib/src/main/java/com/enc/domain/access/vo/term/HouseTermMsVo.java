package com.enc.domain.access.vo.term;

import lombok.Data;

/**
 * @description:
 * @author: ZhangK-Bond
 * @Date: 2018/12/25
 */
@Data
public class HouseTermMsVo {
    /**
     * 项目编号(必填)
     */
    private String projectID;
    /**
     * 获取数据的索引，从1开始(必填)
     */
    private int index;
    /**
     * 获取数据的数量(必填)
     */
    private int count;
    /**
     * 房屋ID (非必填)
     */
    // private int id;
    /**
     * 楼栋ID (非必填)
     */
    private int buildingID;

    /**
     * 身份标识(必填)
     */
    private String token;

}
