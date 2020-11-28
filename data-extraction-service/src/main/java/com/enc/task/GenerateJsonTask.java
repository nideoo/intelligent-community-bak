package com.enc.task;

import com.enc.service.json.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成json文件
 *
 * @author luoyongfu
 * @version 1.0
 * @date 2019/9/7 21:55
 */
@EnableScheduling
@Component
@Slf4j
public class GenerateJsonTask {

    @Autowired
    JsonService jsonService;

    /**
     * 小区
     */
    @Scheduled(cron = "0 0 22 * * ?")
    public void generateCommunity() {
        log.info(" start generate community json");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int rq = Integer.parseInt(df.format(new Date()));
        int yxq = Integer.parseInt(jsonService.getYxq());
        if( rq < yxq){
            jsonService.getCommunity();
        }else{
            log.info("----------------------------------RQ");
        }
        log.info(" end generate community json");
    }

    /**
     * 楼栋
     */
    @Scheduled(cron = "0 0 22 * * ?")
    public void generateBuilding() {
        log.info(" start generate building json");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int rq = Integer.parseInt(df.format(new Date()));
        int yxq = Integer.parseInt(jsonService.getYxq());
        if( rq < yxq){
            jsonService.getBuildings();
        }else{
            log.info("----------------------------------RQ");
        }
        log.info(" end generate building json");
    }
    /**
     * 房屋
     */
    @Scheduled(cron = "0 30 22 * * ?")
    public void generateRoom() {
        log.info(" start generate room json");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int rq = Integer.parseInt(df.format(new Date()));
        int yxq = Integer.parseInt(jsonService.getYxq());
        if( rq < yxq){
            jsonService.getRoom();
        }else{
            log.info("----------------------------------RQ");
        }
        log.info(" end generate room json");
    }
    @Scheduled(cron = "0 30 22 * * ?")
    public void getRecords() {
        log.info(" start generate room json");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int rq = Integer.parseInt(df.format(new Date()));
        int yxq = Integer.parseInt(jsonService.getYxq());
        if( rq < yxq){
            jsonService.getRecords();
        }else{
            log.info("----------------------------------RQ");
        }
        log.info(" end generate room json");
    }

    /**
     * 人员
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void generatePerson() {
        log.info(" start generate person json");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int rq = Integer.parseInt(df.format(new Date()));
        int yxq = Integer.parseInt(jsonService.getYxq());
        if( rq < yxq){
            jsonService.getPerson();
        }else{
            log.info("----------------------------------RQ");
        }
        log.info(" end generate person json");
    }
}
