package com.enc.service;

import com.enc.domain.ResponseResult;

public interface PersonsService {

    //人员信息查询接口-一次性跑完接口
    void savePersonsBatch();

    /***
     * 保存
     * @param id
     */
    void savePersons(int id);

    /***
     * 删除
     * @param personId
     */
    void delPersonInfo(int personId);

    void queryPersonsBatch();

}
