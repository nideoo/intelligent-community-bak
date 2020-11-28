package com.enc.domain.access.vo.term;

import lombok.Data;

/**
 * @description: 获取区域组信息
 * @author: ZhangK-Bond
 * @Date: 2018/12/27
 */
@Data
public class AreaGroupTermVo {

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
     * 身份标识
     */
    private String token;

    /***
     * 父ID
     */
    private String parentID;

}
