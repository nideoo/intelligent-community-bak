package com.enc.dao.mapper.history;

import com.enc.domain.platform.vo.history.pedestrian.PedestrianListQueryVo;

import java.util.List;
import java.util.Map;

public interface PedestrianHistoryMapper {

    /***
     * 获取过往行人列表
     * @param vo
     * @return
     */
    List<Map<String,Object>> getHisPedestrianListSql(PedestrianListQueryVo vo);

    List<Map<String,Object>> getHisPedestrianTopSql(String date);

}
