package com.enc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.access.vo.term.ToKenTermVo;
import com.enc.service.MsTokenService;
import com.enc.utils.http.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-14 16:06
 */
@Service
public class MsTokenServiceImpl implements MsTokenService {

    @Value("${mais.project-id}")
    private String PROJECTID;

    @Value("${mais.server}")
    private String MAIS_SERVER;

    @Value("${mais.user-name}")
    private String MAIS_USERNAME;

    @Value("${mais.password}")
    private String MAIS_PASSWORD;

    @Override
    public String getToKen(){
        ToKenTermVo toKenTermVo = new ToKenTermVo();
        toKenTermVo.setUserName(this.MAIS_USERNAME);
        toKenTermVo.setPassWord(this.MAIS_PASSWORD);
        String jsonToken = JSON.toJSONString(toKenTermVo);
        JSONObject tJsonToken = JSONObject.parseObject(jsonToken);
        String rTokenStr = HttpRequest.sendPost(this.MAIS_SERVER+IntelligentCommunityStatic.GET_TOKEN_URL, tJsonToken,"utf-8");
        JSONObject rJson = JSONObject.parseObject(rTokenStr);
        if(rJson.getString("resultCode").equals("0")){
            return rJson.getString("token");
        }
        return "";
    }
}