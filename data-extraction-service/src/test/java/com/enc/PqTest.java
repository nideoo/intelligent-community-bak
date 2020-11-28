package com.enc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.enc.domain.access.entity.CardInfoEntity;
import com.enc.domain.access.entity.PersonsEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PqTest {
    public static void main(String[] args){
        String rStr="{\"resultCode\":0,\"msg\":\"\",\"totalCount\":100,\"persons\":[{\"id\":12345,\"name\":\"张三\",\"cardInfo\":[{\"cardNo\":\"123\",\"startTime\":\"2018-11-11 10:10:10\"},{\"cardNo\":\"256\"}]},{\"id\":12345,\"name\":\"张三\",\"cardInfo\":{}}]}";
        JSONObject rJson = JSONObject.parseObject(rStr);
        JSONArray jsonArray=rJson.getJSONArray("persons");
        for(int i=0;i<jsonArray.size();i++){
            JSONObject json = JSONObject.parseObject(jsonArray.get(i).toString());
            PersonsEntity v1=json.toJavaObject(PersonsEntity.class);
            System.out.print(v1);
            JSONArray cardInfoJson=json.getJSONArray("cardInfo");
            for(i=0;i<cardInfoJson.size();i++){
                JSONObject js = JSONObject.parseObject(cardInfoJson.get(i).toString());
                CardInfoEntity cv=js.toJavaObject(CardInfoEntity.class);
                System.out.println(cv);
            }
        }
        log.info("ssss");
    }
}
