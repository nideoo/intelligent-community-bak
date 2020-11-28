package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.dao.mapper.PersonMapper;
import com.enc.domain.ResponseResult;
import com.enc.domain.access.entity.*;
import com.enc.domain.access.vo.PersonVo;
import com.enc.domain.access.vo.person.PersonPushRespVo;
import com.enc.domain.access.vo.CardInfoVo;
import com.enc.domain.access.vo.person.SinglePersonVo;
import com.enc.service.MsTokenService;
import com.enc.service.PersonsService;
import com.enc.utils.base64img.ConvertBase64ToImage;
import com.enc.utils.http.HttpRequest;
import com.enc.utils.idCard.IDCardValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PersonsServiceImpl implements PersonsService {

    @Autowired
    private MsTokenService msTokenService;

    @Autowired
    private PersonMapper personMapper;

    @Value("${mais.persion.url}")
    private String personImgUrl;

    @Value("${mais.persion.data-img-url}")
    private String personDataImgUrl;

    @Value("${mais.persion.query-persons}")
    private String queryPersons;


    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;

    @Override
    /*public void savePersonsBatch() {
        //一次性插入前先清除数据
        PersonVo o = new PersonVo();
        int index = 1;
        String token = msTokenService.getToKen();
        o.setToken(token);
        o.setProjectID(PROJECTID);
        o.setDetail(1);
        o.setCount(10);
        o.setIndex(index);
        String result = null;
        for (int i = index; ; i = o.getIndex() + o.getCount()) {
            o.setIndex(i);
            result = HttpRequest.sendPost(MAIS_SERVER + IntelligentCommunityStatic.GET_PERSONS_URL,JSONObject.toJSONString(o),"utf-8");
            save(result,token);
        }

    }*/

    public void savePersonsBatch() {
        log.info("--------------savePersonsBatch------------");
        //一次性插入前先清除数据
        ResponseResult responseResult = new ResponseResult();
        PersonVo o = new PersonVo();
        int index = 1;
        // int index = 21351;
        String token = msTokenService.getToKen();
        o.setToken(token);
        o.setProjectID(PROJECTID);
        o.setDetail(1);
        o.setCount(10);
        o.setIndex(index);
        String result = null;
        for (int i = index; ; i = o.getIndex() + o.getCount()) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            o.setIndex(i);
            log.info("------i:"+i);
            result = HttpRequest.sendPost(MAIS_SERVER + IntelligentCommunityStatic.GET_PERSONS_URL,JSONObject.toJSONString(o),"utf-8");
            JSONObject rJson = JSONObject.parseObject(result);
            if (!rJson.getString("resultCode").equals("0")) {
                responseResult.setMsg(rJson.getString("msg"));
            }
            PersonPushRespVo personPushVo = JSON.parseObject(result, PersonPushRespVo.class);
            if(personPushVo.getTotalCount()==0){
                log.info("CW:"+i);
                log.info("result:"+result.toString());
                continue;
            }else{
                JSONArray rArray = rJson.getJSONArray("persons");
                for(int j = 0; j < rArray.size(); j++){
                    log.info("------i:"+i+"---j.size:"+rArray.size()+"------j:"+j);
                    JSONObject personsJson = JSONObject.parseObject(rArray.get(j).toString());
                    PersonsEntity pEntity = personsJson.toJavaObject(PersonsEntity.class);
                    pEntity.setParentID(pEntity.getParentID().replace("[","").replace("]",""));
                    if(pEntity.getIdNumber().length() != 0){
                        try{
                            if(pEntity.getIdNumber().length() == 18){
                                pEntity.setGender(IDCardValidator.getCarInfo18W(pEntity.getIdNumber()));
                            }
                            if(pEntity.getIdNumber().length() == 15){
                                pEntity.setGender(IDCardValidator.getCarInfo18W(pEntity.getIdNumber()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pEntity.setPersonId(pEntity.getIdNumber().substring(0,2));
                    }else{
                        pEntity.setGender("");
                        pEntity.setPersonId("");
                    }

                    // bond 2019-09-29
                    // if(personMapper.personCountById(pEntity.getId()+"") == 0){

                        log.info("=======:"+pEntity.getId()+":"+personMapper.addPerson(pEntity));

                        //解析cardInfo对象并保存
                        //2.100.2、获取人员卡信息
                        CardInfoVo cVo = new CardInfoVo();
                        cVo.setId(pEntity.getId().toString());
                        cVo.setProjectID(PROJECTID);
                        cVo.setToken(token);
                        // String cardInfoResult = HttpRequest.sendPost(MAIS_SERVER + IntelligentCommunityStatic.GET_CARD_INFO_URL,JSONObject.toJSONString(cVo),"utf-8");

                        //解析detailInfo对象并保存
                        if(personsJson.get("detailInfo") != null){
                            String detailString = personsJson.getString("detailInfo");
                            JSONObject detailObject = JSONObject.parseObject(detailString);;
                            if(detailString != null){
                                DetailInfoEntity dEntity = detailObject.toJavaObject(DetailInfoEntity.class);
                                dEntity.setPersonId(personsJson.getLong("id"));

                                if(!StringUtils.isEmpty(dEntity.getPhoto())){
                                    String photoName = UUID.randomUUID()+".jpg";
                                    ConvertBase64ToImage.generateImage((personImgUrl+personDataImgUrl),photoName,dEntity.getPhoto().replace("base64=data:image/jpg;base64,",""));
                                    dEntity.setPhoto(personDataImgUrl+photoName);
                                }
                                if(!StringUtils.isEmpty(dEntity.getRealTimePhoto())){
                                    String realTimePhotoName = UUID.randomUUID()+".jpg";
                                    ConvertBase64ToImage.generateImage((personImgUrl+personDataImgUrl),realTimePhotoName,dEntity.getRealTimePhoto().replace("base64=data:image/jpg;base64,",""));
                                    dEntity.setRealTimePhoto(personDataImgUrl+realTimePhotoName);
                                }
                                if(!StringUtils.isEmpty(dEntity.getOtherPhoto())){
                                    String otherPhotoName = UUID.randomUUID()+".jpg";
                                    ConvertBase64ToImage.generateImage((personImgUrl+personDataImgUrl),otherPhotoName,dEntity.getOtherPhoto().replace("base64=data:image/jpg;base64,",""));
                                    dEntity.setOtherPhoto(personDataImgUrl+otherPhotoName);
                                }
                                personMapper.addDetailInfo(dEntity);
                            }
                        }
                    // }// 1 2019-09-29
                }
            }
        }
    }


    private void save(String result,String token){
        JSONObject rJson = JSONObject.parseObject(result);

        PersonPushRespVo personPushVo = JSON.parseObject(result, PersonPushRespVo.class);
        if(personPushVo.getTotalCount()==0){

        }else{
            JSONArray rArray = rJson.getJSONArray("persons");
            for(int j = 0; j < rArray.size(); j++){
                JSONObject personsJson = JSONObject.parseObject(rArray.get(j).toString());
                PersonsEntity pEntity = personsJson.toJavaObject(PersonsEntity.class);
                pEntity.setParentID(pEntity.getParentID().replace("[","").replace("]",""));
                if(pEntity.getIdNumber().length() != 0){
                    try{
                        if(pEntity.getIdNumber().length() == 18){
                            pEntity.setGender(IDCardValidator.getCarInfo18W(pEntity.getIdNumber()));
                        }
                        if(pEntity.getIdNumber().length() == 15){
                            pEntity.setGender(IDCardValidator.getCarInfo18W(pEntity.getIdNumber()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pEntity.setPersonId(pEntity.getIdNumber().substring(0,2));
                }else{
                    pEntity.setGender("");
                    pEntity.setPersonId("");
                }
                personMapper.addPerson(pEntity);

                //解析cardInfo对象并保存
                //2.100.2、获取人员卡信息
                CardInfoVo cVo = new CardInfoVo();
                cVo.setId(pEntity.getId().toString());
                cVo.setProjectID(PROJECTID);
                cVo.setToken(token);
                String cardInfoResult = HttpRequest.sendPost(MAIS_SERVER + IntelligentCommunityStatic.GET_CARD_INFO_URL,JSONObject.toJSONString(cVo),"utf-8");
                JSONObject cardInfoJson = JSONObject.parseObject(cardInfoResult);
                JSONArray cardInfoJsonArr = JSONArray.parseArray(cardInfoJson.get("cardInfo").toString());
                if(cardInfoJsonArr != null){
                    for(int k = 0; k < cardInfoJsonArr.size(); k++){
                        JSONObject cardInfo = JSONObject.parseObject(cardInfoJsonArr.get(k).toString());
                        CardInfoEntity cEntity =cardInfo.toJavaObject(CardInfoEntity.class);
                        cEntity.setPersonId(cardInfoJson.getString("id"));
                        personMapper.addCardInfo(cEntity);
                    }
                }
                //解析detailInfo对象并保存
                if(personsJson.get("detailInfo") != null){
                    String detailString = personsJson.getString("detailInfo");
                    JSONObject detailObject = JSONObject.parseObject(detailString);;
                    if(detailString != null){
                        DetailInfoEntity dEntity = detailObject.toJavaObject(DetailInfoEntity.class);
                        dEntity.setPersonId(personsJson.getLong("id"));
                        if(!StringUtils.isEmpty(dEntity.getPhoto())){
                            String photoName = UUID.randomUUID()+".jpg";
                            ConvertBase64ToImage.generateImage((personImgUrl+personDataImgUrl),photoName,dEntity.getPhoto().replace("base64=data:image/jpg;base64,",""));
                            dEntity.setPhoto(personDataImgUrl+photoName);
                        }
                        if(!StringUtils.isEmpty(dEntity.getRealTimePhoto())){
                            String realTimePhotoName = UUID.randomUUID()+".jpg";
                            ConvertBase64ToImage.generateImage((personImgUrl+personDataImgUrl),realTimePhotoName,dEntity.getRealTimePhoto().replace("base64=data:image/jpg;base64,",""));
                            dEntity.setRealTimePhoto(personDataImgUrl+realTimePhotoName);
                        }
                        if(!StringUtils.isEmpty(dEntity.getOtherPhoto())){
                            String otherPhotoName = UUID.randomUUID()+".jpg";
                            ConvertBase64ToImage.generateImage((personImgUrl+personDataImgUrl),otherPhotoName,dEntity.getOtherPhoto().replace("base64=data:image/jpg;base64,",""));
                            dEntity.setOtherPhoto(personDataImgUrl+otherPhotoName);
                        }
                        personMapper.addDetailInfo(dEntity);
                    }

                    //解析entourage1（在detailinfo对象中）
                    if(detailObject.get("entourage1") != null){
                        String entourage1String = detailObject.getString("entourage1");
                        if(entourage1String != null){
                            JSONObject entourage1Object = JSONObject.parseObject(entourage1String);
                            EntourageEntity eEntity = entourage1Object.toJavaObject(EntourageEntity.class);
                            eEntity.setPersonId(personsJson.getLong("id"));
                            //保存entourage1
                            personMapper.addEntourage(eEntity);
                        }
                    }
                    if(detailObject.get("entourage2") != null){
                        //解析entourage2（在detailinfo对象中）
                        String entourage2String =detailObject.getString("entourage2");
                        if(entourage2String != null){
                            JSONObject entourage2Object=JSONObject.parseObject(entourage2String);
                            EntourageEntity eEntity = entourage2Object.toJavaObject(EntourageEntity.class);
                            eEntity.setPersonId(personsJson.getLong("id"));
                            //保存entourage2
                            personMapper.addEntourage(eEntity);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void savePersons(int id) {
        ResponseResult responseResult = new ResponseResult();
        SinglePersonVo o = new SinglePersonVo();
        int index = 1;
        String token = msTokenService.getToKen();
        o.setToken(token);
        o.setProjectID(PROJECTID);
        o.setPersonID(id);
        o.setDetail(1);
        o.setCount(10);
        o.setIndex(index);
        String result = null;
        for (int i = index; ; i = o.getIndex() + o.getCount()) {
            o.setIndex(i);
            result = HttpRequest.sendPost(MAIS_SERVER + IntelligentCommunityStatic.GET_PERSONS_URL,JSONObject.toJSONString(o),"utf-8");
            save(result,token);
        }
    }

    @Override
    public void delPersonInfo(int personId) {
        personMapper.deletePerson(personId);
        personMapper.deleteDetailInfo(personId);
        personMapper.deteleCardInfo(personId);
        personMapper.deleteEntourage(personId);
    }

    @Override
    public void queryPersonsBatch() {

        int index = 0;
        int count = 100;
        for(int i = 0; i < 1230; i++){
            if(i == 0){
                index = 1;
            }else{
                index = i * count +1;
            }
            PersonVo o = new PersonVo();
            o.setProjectID(PROJECTID);
            o.setIndex(index);
            o.setCount(count);
            o.setDetail(1);
            // o.setToken(msTokenService.getToKen());
            String result = HttpRequest.sendPost(MAIS_SERVER + IntelligentCommunityStatic.QUERY_PERSON_URL,JSONObject.toJSONString(o),"utf-8");

        }


    }

    public static void main(String[] args) {
        int index = 0;
        int count = 2;
        for(int i = 0; i < 1230; i++){
            if(i == 0){
                index = 1;
            }else{
                index = i * count +1;
            }
            PersonVo o = new PersonVo();
            o.setProjectID("JN01");
            o.setIndex(index);
            o.setCount(count);
            o.setDetail(1);
            o.setToken("c149c92c42444297a6a5393c73cbe3bd");
            String result = HttpRequest.sendPost("http://60.255.139.164:3741" + IntelligentCommunityStatic.QUERY_PERSON_URL,JSONObject.toJSONString(o),"utf-8");
            JSONObject json = JSONObject.parseObject(result);
            JSONArray personsArray = json.getJSONArray("persons");
            for(Object obj : personsArray){
                JSONObject objJson = JSONObject.parseObject(obj.toString());
                QueryPersonsEntity queryPersonsEntity = new QueryPersonsEntity();
                queryPersonsEntity.setPersonId(objJson.getInteger("personID"));
                queryPersonsEntity.setAreaId(objJson.getInteger("areaID"));
                queryPersonsEntity.setRoomId(objJson.getInteger("roomID"));
                queryPersonsEntity.setPersonName(objJson.getString("personName"));
                queryPersonsEntity.setSocialType(objJson.getString("socialType"));
                queryPersonsEntity.setSocialNumber(objJson.getString("socialNumber"));
                queryPersonsEntity.setLandlordRelative(objJson.getString("landlordRelative"));
                if(!objJson.getString("photo").equals("")){
                    String realTimePhotoName = UUID.randomUUID()+".jpg";
                    // ConvertBase64ToImage.generateImage((personImgUrl+queryPersons),realTimePhotoName,objJson.getString("photo").replace("base64=data:image/jpg;base64,",""));
                    ConvertBase64ToImage.generateImage(("C:\\tmp\\ms\\query-persons\\"),realTimePhotoName,objJson.getString("photo").replace("base64=data:image/jpg;base64,",""));
                    // dEntity.setRealTimePhoto(personDataImgUrl+realTimePhotoName);
                    // queryPersonsEntity.set(personDataImgUrl+realTimePhotoName);
                }

                queryPersonsEntity.setPhoto(objJson.getString("photo"));
                queryPersonsEntity.setPhone(objJson.getString("phone"));
                queryPersonsEntity.setRegTime(objJson.getString("regTime"));

            }
            System.out.println();
        }
    }

}

























