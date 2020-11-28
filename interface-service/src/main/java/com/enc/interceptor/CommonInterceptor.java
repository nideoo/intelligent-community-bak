package com.enc.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author: BOND
 * @description: 请求拦截器，处理跨域问题
 * @create: 2019-05-21 21:51
 */
@Component
public class CommonInterceptor implements HandlerInterceptor {

    @SuppressWarnings("deprecation")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        // response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept,token");


        String uri = request.getRequestURI();
        if(uri.equals("/api/v1/login") || uri.equals("/camera-receive/face") || uri.equals("/camera-receive/vehicle") ){
            return true;
        }
        String token =null;
        if(request instanceof HttpServletRequest){
            token = request.getHeader("token");
        }
        /** // 2020-11-28
        if (StringUtils.isEmpty(token)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            out = response.getWriter();
            JSONObject failJson = new JSONObject();
            failJson.put("code","401");
            failJson.put("msg","系统未登陆");
            failJson.put("obj","");
            out.append(failJson.toString());
            return false;
        }
        **/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }

}