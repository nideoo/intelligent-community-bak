package com.enc.dao.mapper.humanHousingMgt;

import com.enc.domain.platform.vo.humanHousingMgt.TreeTermVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HouseOperationMapper {

    /***
     * 获取人房管理树-SQL
     * @param vo
     * @return
     */
    List<Map<String,Object>> getAreaGroupTreeSql(TreeTermVo vo);

    /***
     * 获取人房管理树-楼栋-SQL
     * @param communityId 小区ID
     * @return
     */
    List<Map<String,Object>> getBuildingsTreeSql(String communityId);

    /***
     *  根据区域组ID获取相对应的详情
     * @param areaGroupId
     * @return
     */
    Map<String,Object>getHouseOperationDetailsSql(String areaGroupId);

    /***
     * 获取某栋楼的层数
     * @param buildingId
     * @return
     */
    int getMaxFloorSql(String buildingId);

    /***
     * 获取楼栋内房间
     * @param buildingId
     * @return
     */
    List<Map<String,Object>> getRoomSql(String buildingId);

    /***
     * 获取房屋内人数
     * @param roomId
     * @return
     */
    int getRoomPersonCouSql(String roomId);

    /***
     * 根据房屋ID获取详情
     * @param roomId
     * @return
     */
    List<Map<String,Object>> getRoomDetailsSql(String roomId);

    /***
     * 根据ID获取房屋统计
     * @param id
     * @return
     */
    Map<String,Object> getRoomSumByIdSql(String id);

    /***
     * 房屋使用类型统计
     * @param id
     * @return
     */
    List<Map<String,Object>> getRoomTypeSumByIdSql(String id);

    /***
     * 获取楼栋层数
     * @param id
     * @return
     */
    String getTotalFloor(String id);

    /***
     * 获取楼栋常驻-流动人数汇总
     * @param id
     * @return
     */
    List<Map<String,Object>>  getLdRsCou(String id);

    /***
     * 获取楼栋重点关注
     * @param id
     * @return
     */
    String getZdgzCou(String id);

    /***
     * 获取区 流动-常驻人口
     * @param id
     * @return
     */
    List<Map<String,Object>> getAreaTypeCou(String id);

    /***
     * 小区重点关注人员汇总
     * @param id
     * @return
     */
    String getXqZdgzCou(String id);

    /***
     *
     * @param id
     * @return
     */
    Map<String,Object> getGzType(String id);

    Map<String,Object> getAreaGroupInfo(String id);

}




















