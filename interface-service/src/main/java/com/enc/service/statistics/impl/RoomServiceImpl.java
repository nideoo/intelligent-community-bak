package com.enc.service.statistics.impl;

import com.enc.dao.mapper.statistics.RoomMapper;
import com.enc.domain.platform.vo.statistics.RoomListVo;
import com.enc.service.statistics.RoomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-06-03 14:58
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public PageInfo getRoomList(RoomListVo vo) {
        PageHelper.startPage(vo.getPageIndex(),vo.getPageSize());
        List<Map<String,Object>> list = roomMapper.getRoomListSql(vo);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> initSelect(String id, String type) {
        List<Map<String,Object>> list = null;
        if(type.equals("jdxq")){
            if(id == null || id.equals("")){
                id = "1879053997";
            }
            list = roomMapper.getStreetCommByIdListSql(id);
        }
        if(type.equals("ld")){
            list = roomMapper.getBuildingsByIdListSql(id);
        }
        return list;
    }

    @Override
    public Map<String, Object> getRoomInfo(String id) {
        return roomMapper.getRoomInfo(id);
    }
}




















