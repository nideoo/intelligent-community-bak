package com.enc.service.history;

import com.enc.domain.platform.vo.history.pedestrian.PedestrianListQueryVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface PedestrianHistoryService {

    /***
     * 获取过往行人列表
     * @param vo
     * @return
     */
    PageInfo getHisPedestrianList(PedestrianListQueryVo vo);

}
