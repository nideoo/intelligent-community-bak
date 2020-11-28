package com.enc.service.statistics;

import com.enc.domain.platform.vo.statistics.RoomListVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/***
 * 房屋
 */
public interface RoomService {

    PageInfo getRoomList(RoomListVo vo);

    List<Map<String,Object>> initSelect(String id,String type);

    Map<String,Object> getRoomInfo(String id);

}
