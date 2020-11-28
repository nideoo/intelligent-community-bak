package com.enc.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.enc.constants.IntelligentCommunityStatic;
import com.enc.domain.ResponseResult;
import com.enc.domain.platform.vo.login.LoginVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: BOND
 * @description:
 * @create: 2019-06-28 14:18
 */
@Api(value = "登陆-controller")
@RestController
@RequestMapping("/api/")
public class LoginViewController {
    // 预先设置好的正确的用户名和密码，用于登录验证
    private String rightUserName = "admin";
    private String rightPassword = "admin123";
    private int authorizeDate = 20220627;

    /**
     * 登录校验
     *
     * @param request
     * @return
     */
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody LoginVo vo, HttpServletRequest request) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int xtrq = Integer.parseInt(dateFormat.format(new Date()));
        if(authorizeDate < xtrq ){
            return ResponseResult.fail("系统异常请联系管理员！");
        }
        if(StringUtils.isEmpty(vo.getUsername())){
            return ResponseResult.fail("用户名不能为空！");
        }
        if(StringUtils.isEmpty(vo.getPassword())){
            return ResponseResult.fail("密码不能为空！");
        }
        if(!vo.getUsername().equals(rightUserName)){
            return ResponseResult.fail("用户名不存在！");
        }
        if(!vo.getPassword().equals(rightPassword)){
            return ResponseResult.fail("密码不正确！");
        }

        // 防止缓存没有更新 所以前面首先更新登录时间，伴随着跟新缓存
        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();
        session.setAttribute("token", session.getId());
        session.setMaxInactiveInterval(24 * 60 * 60);
        data.put("token", session.getId());
        data.put("loginName",rightUserName);

        return ResponseResult.success(data);
    }

    /**
     * 推出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = IntelligentCommunityStatic.VERSION+"/signOut", method = RequestMethod.GET)
    public ResponseResult signOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        // request.getSession().invalidate();
        return ResponseResult.success("退出成功!");
    }
}