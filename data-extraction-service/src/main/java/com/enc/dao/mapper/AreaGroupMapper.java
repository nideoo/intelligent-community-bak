package com.enc.dao.mapper;

import com.enc.domain.access.entity.AreaGroupEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AreaGroupMapper {

    /***
     * 添加-区域组
     * @param areaGroupDataVo
     * @return
     */
    int addAreaGroupData(AreaGroupEntity areaGroupDataVo);

}