package com.enc.domain.platform.vo.history.pedestrian;

import com.enc.domain.PageInfoVo;
import lombok.Data;

/**
 * @author: BOND
 * @description: 过往行人历史查询Vo
 * @create: 2019-05-22 13:36
 */
@Data
public class PedestrianListQueryVo extends PageInfoVo {

    /***
     * 名称
     */
    private String name;

    /***
     * 身份证
     */
    private String identityNum;

    /****
     * 相似度
     */
    private String scoreRecg;

    /***
     * 开始日期
     */
    private String beginDate;

    /***
     * 结束日期
     */
    private String endDate;

}