package com.enc.service;

import com.enc.domain.ResponseResult;

/**
 * @description:
 * @author: ZhangK-Bond
 * @Date: 2018/12/25
 */
public interface RoomService {

    /***
     * 获取房屋数据
     * @return
     */
    ResponseResult getRoomDataBatch();

    void saveUpadateRoom();

    void saveRoomById(int id);

    void delRoomById(int id);



}
