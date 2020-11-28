package com.enc.domain.platform.vo.statistics;

import com.enc.domain.PageInfoVo;
import lombok.Data;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-15 17:40
 */
@Data
public class RoomListVo extends PageInfoVo {

    /***
     * 街道  id
     */
    private String streetId;

    /***
     * 小区  id
     */
    private String communityId;

    /***
     * 楼栋 id
     */
    private String buildingId;

    /***
     *  房屋等级-3.26
     */
    private String roomLevel;

    /***
     * 房屋使用类型-3.27
     */
    private String roomType;

}