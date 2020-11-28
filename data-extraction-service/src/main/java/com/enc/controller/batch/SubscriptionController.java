package com.enc.controller.batch;

import com.enc.domain.ResponseResult;
import com.enc.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-23 18:12
 */
@Api("订阅通知")
@RequestMapping("/")
@RestController
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @ApiOperation(value="设置订阅通知", notes="", httpMethod="GET")
    // @RequestMapping(value = "/set-subscribe", method = RequestMethod.GET)
    public ResponseResult setSubscribe(){
        subscriptionService.setSubscribe();
        return ResponseResult.success("订阅成功！");
    }

}













