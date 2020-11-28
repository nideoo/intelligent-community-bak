package com.enc.service;

import com.enc.domain.ResponseResult;

public interface BuildingsService {


    /***
     * 获取楼栋信息
     * @return
     */
    ResponseResult getBuildingsBatch();

    void delBuildingsById(int id);

    void saveBuildingsById(int id);

}
