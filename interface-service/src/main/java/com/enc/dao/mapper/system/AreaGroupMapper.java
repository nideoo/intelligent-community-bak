package com.enc.dao.mapper.system;

import com.enc.domain.platform.vo.areaGroup.AreaGroupListVo;
import com.enc.domain.platform.vo.areaGroup.AreaGroupUpdateVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AreaGroupMapper {
    /***
     * 获取区域组列表
     * @return
     */
    List<Map<String,Object>> getAreaGroupListSql(AreaGroupListVo vo);

    void areaGroupUpdateSql(AreaGroupUpdateVo vo);
}
