package com.enc.service.json.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.enc.dao.mapper.JsonMapper;
import com.enc.service.json.JsonService;
import com.enc.util.DateFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JsonServiceImpl implements JsonService {


    /**
     * 指定的查询表
     *  1：房屋
     *  2：人员
     */
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    
    private static final  String ROOTPATH = "\\home\\bocom\\zhxq";

    @Autowired
    JsonMapper jsonMapper;

    @Override
    public void getCommunity() {
        // CreateFileUtil util = new CreateFileUtil();
        List<Map<String,Object>> list = jsonMapper.getCommunity();
        log.info("getCommunity本批次查询数据条数>>>>>>" + list.size());
        JSONArray jsonObject = JSONArray.parseArray(JSON.toJSONString(list));
        String jsonString1 = jsonObject.toString();
        String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator+"community"+File.separator;
        /*
        CreateFileUtil.createJsonFile(jsonString1,ROOTPATH + "\\community","xq_" +
                DateFormat.formatDate(new Date(), DateFormat.PATTERN_DATE));
        */
        CreateFileUtil.createJsonFile(jsonString1,generatePath,"xq_" +
                DateFormat.formatDate(new Date(), DateFormat.PATTERN_DATE));

    }

    /**
     * 楼栋
     */
    @Override
    public void getBuildings() {
        List<Map<String,Object>> list = jsonMapper.getBuildings();
        log.info("getBuildings本批次查询数据条数>>>>>>" + list.size());
        String name = "ld_" + DateFormat.formatDate(new Date(), DateFormat.PATTERN_DATE);
        // String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator+"building"+File.separator;
        String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator;
        // createJsonFile(list, ROOTPATH + "\\building", name);
        createJsonFile(list, generatePath, name);
    }

    @Override
    public void getRoom() {
        Integer pageIndex = 1;
        Integer pageSize = 200000;

        // String generatePath = ROOTPATH + "\\room\\";
        // String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator+"room"+File.separator;
        String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator;
        Integer pageNum = this.pageRoom(pageIndex, pageSize, generatePath);

        pageIndex++;
        for (; pageIndex <= pageNum; pageIndex++) {
            this.pageRoom(pageIndex, pageSize, generatePath);
        }
    }

    @Override
    public void getRecords() {
        Integer pageIndex = 1;
        Integer pageSize = 200000;

        // String generatePath = ROOTPATH + "\\records\\";
        // String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator+"records"+File.separator;
        String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator;
        Integer pageNum = this.pageRecords(pageIndex, pageSize, generatePath);

        pageIndex++;
        for (; pageIndex <= pageNum; pageIndex++) {
            this.pageRecords(pageIndex, pageSize, generatePath);
        }
    }

    @Override
    public void getSingleRecords(String jsonStr) {
        // String generatePath = ROOTPATH + "\\records\\";
        // String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator+"records"+File.separator;
        String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator;
        String name = "sk_" + DateFormat.formatDate(new Date(), DateFormat.PATTERN_DATE + "_" + System.currentTimeMillis());
        CreateFileUtil.createJsonFile(jsonStr,generatePath,name);
    }

    @Override
    public void getPerson() {
        // String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator+"person"+File.separator;
        String generatePath = File.separator+"home"+File.separator+"bocom"+File.separator+"zhxq"+File.separator;
        this.pagePerson(generatePath);
    }



    private Integer pagePerson(String generatePath) {
        Integer pageSize = 10000;
        Integer querySize = pageSize;
        Integer page = 0;
        while (querySize.intValue() == pageSize) {
            page++;
            String name = "ry_" + DateFormat.formatDate(new Date(), DateFormat.PATTERN_DATE + "_" + page);
            Integer start = (page - 1) * pageSize;
            List<Map<String, Object>> list = jsonMapper.getPerson(start, pageSize);
            log.info("getPerson本批次查询数据条数 page= " + page + ">>>>>>" + list.size());
            this.createJsonFile(list, generatePath, name);
            querySize = list.size();
        }
        return page;
    }

    /**
     * 分页生成文件
     * @param pageIndex : 页数
     * @param pageSize : 页大小
     * @param generatePath : 生成路径
     * @return  Integer
     */
    private Integer pageRoom(Integer pageIndex, Integer pageSize, String generatePath) {
        List<Map<String, Object>> list;
        String name = "fw_" + DateFormat.formatDate(new Date(), DateFormat.PATTERN_DATE +"_" + pageIndex);
        PageHelper.startPage(pageIndex, pageSize);
        list = jsonMapper.getRoom();
        log.info("getRoom本批次查询数据条数 page= " + pageIndex + " >>>>>>" + list.size());
        PageInfo pageInfo = new PageInfo(list);
        int pageNum = pageInfo.getPages();
        this.createJsonFile(pageInfo.getList(), generatePath, name);
        return pageNum;
    }

    private Integer pageRecords(Integer pageIndex, Integer pageSize, String generatePath) {
        List<Map<String, Object>> list;
        String name = "sk_" + DateFormat.formatDate(new Date(), DateFormat.PATTERN_DATE +"_" + pageIndex);
        PageHelper.startPage(pageIndex, pageSize);
        list = jsonMapper.getRecords(DateFormat.formatDate(new Date(),"yyyy-MM-dd"));
        log.info("getRoom本批次查询数据条数 page= " + pageIndex + " >>>>>>" + list.size());
        PageInfo pageInfo = new PageInfo(list);
        int pageNum = pageInfo.getPages();
        this.createJsonFile(pageInfo.getList(), generatePath, name);
        return pageNum;
    }


    private void createJsonFile(List result, String generatePath, String fileName) {
        JSONArray jsonObject = JSONArray.parseArray(JSON.toJSONString(result));
        String jsonString1 = jsonObject.toString();
        CreateFileUtil.createJsonFile(jsonString1,generatePath, fileName);
    }

    @Override
    public String getYxq() {
        return jsonMapper.getYxq();
    }

    public static void main(String[] args) {
        log.info(DateFormat.formatDate(new Date(),"yyyy-MM-dd HH:MM"));
    }


}
