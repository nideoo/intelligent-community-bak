package com.enc.domain.platform.vo.history.vehicle;

import com.enc.domain.PageInfoVo;
import lombok.Data;

/**
 * @author: BOND
 * @description: 过往车辆历史查询Vo
 * @create: 2019-05-22 13:36
 */
@Data
public class VehicleListQueryVo extends PageInfoVo {

    /***
     * 车牌号
     */
    private String carPlate;

    /***
     * 相机编号
     */
    private String cameraId;

    /***
     * 开始日期
     */
    private String beginDate;

    /***
     * 结束日期
     */
    private String endDate;



}