package com.enc.dao.mapper;

import com.enc.domain.access.entity.BuildingsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuildingsMapper {

    /***
     * 添加-楼栋
     * @param buildingsDataVo
     * @return
     */
    int addBuildingsData(BuildingsEntity buildingsDataVo);

    List<Map<String,Object>> getbuildingsId();

    void delBuildingsByIdSql(int id);

}