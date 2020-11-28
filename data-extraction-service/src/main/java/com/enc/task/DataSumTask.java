package com.enc.task;

import com.enc.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: BOND
 * @description:
 * @create: 2019-06-04 14:08
 */
@Slf4j
@Component
public class DataSumTask {

    @Autowired
    RoomService roomService;

    // @Scheduled(cron="*/10 * * * * ?")
    // @Scheduled(cron = "0 0 0/1 * * ?")
    public void roomSum(){
        roomService.saveUpadateRoom();
    }

}