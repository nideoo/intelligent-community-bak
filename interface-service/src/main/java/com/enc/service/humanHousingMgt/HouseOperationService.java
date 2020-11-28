package com.enc.service.humanHousingMgt;

import com.alibaba.fastjson.JSONObject;
import com.enc.domain.platform.vo.humanHousingMgt.HumanHousingTermVo;

import java.util.Map;

public interface HouseOperationService {

    /***
     * 获取小区树结构
     * @param communityName
     * @return
     */
    JSONObject areaGroupTree(String communityName);

    /***
     * 根据ID、TYPE查询区、街道、小区详情及汇总
     * @param vo
     * @return
     */
    JSONObject humanHousingDetails(HumanHousingTermVo vo);

    /***
     * 根据楼栋ID查询详情
     * @param buildingId
     * @return
     */
    JSONObject buildingDetails(String buildingId);

    /***
     * 根据房屋ID获取详情
     * @param roomId
     * @return
     */
    Map<String,Object> getRoomDetails(String roomId);

    Map<String,Object> getAreaGroupInfo(String id);


}
