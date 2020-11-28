package com.enc.domain.access.vo.term;

import lombok.Data;

/**
 * @description:
 * @author: ZhangK-Bond
 * @Date: 2018/12/27
 */
@Data
public class BuildingsByIdTermVo {

    /***
     * 项目编号
     */
    private String projectID;

    /***
     * 获取数据的索引
     */
    private int index;

    /***
     * 获取数据的数量
     */
    private int count;

    /***
     * 楼栋ID
     */
    private int id;

    /***
     * 身份标识
     */
    private String token;

}
