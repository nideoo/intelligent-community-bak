package com.enc.domain;

import lombok.Data;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-15 11:22
 */
@Data
public class PageInfoVo {

    /***
     * 每页条数
     */
    private int pageSize;

    /***
     * 当前页
     */
    private int pageIndex;

}