package com.enc.domain.platform.vo.statistics;

import com.enc.domain.PageInfoVo;
import lombok.Data;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-15 17:40
 */
@Data
public class PersonListVo extends PageInfoVo {

    /***
     * 姓名
     */
    private String name;

    /***
     * 身份证号
     */
    private String idCard;

    /***
     * 关注登记 - 标签 9.01
     */
    private String level;

    /***
     * 人口流入地
     */
    private String populInflow;

    /***
     * 人口流入地
     */
    private String populInflow1;

    /***
     * 重点关注人员 3.13
     */
    private String payCloseAttention;

}