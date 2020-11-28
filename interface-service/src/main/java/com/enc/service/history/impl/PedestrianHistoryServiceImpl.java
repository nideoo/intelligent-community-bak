package com.enc.service.history.impl;

import com.enc.dao.mapper.history.PedestrianHistoryMapper;
import com.enc.domain.platform.vo.history.pedestrian.PedestrianListQueryVo;
import com.enc.service.history.PedestrianHistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-21 22:21
 */
@Service
public class PedestrianHistoryServiceImpl implements PedestrianHistoryService {

    @Autowired
    PedestrianHistoryMapper pedestrianHistoryMapper;

    @Override
    public PageInfo getHisPedestrianList(PedestrianListQueryVo vo) {
        PageHelper.startPage(vo.getPageIndex(),vo.getPageSize());
        List<Map<String,Object>> list = pedestrianHistoryMapper.getHisPedestrianListSql(vo);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}