package cn.bjjoy.web.joy_bms.web;

import cn.bjjoy.web.joy_bms.auth.service.CurrentUser;
import cn.bjjoy.web.joy_bms.base.ResponseCode;
import cn.bjjoy.web.joy_bms.base.ResponseResult;
import cn.bjjoy.web.joy_bms.dto.UserDto;
import cn.bjjoy.web.joy_bms.exception.OperationException;
import cn.bjjoy.web.joy_bms.redis.RedisMapService;
import cn.bjjoy.web.joy_bms.service.AuthHttpService;
import cn.bjjoy.web.joy_bms.service.BaseHttpService;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GXM on 2017/11/9
 **/
@RestController
@RequestMapping("/test")
public class Test {

    @Autowired
    AuthHttpService authHttpService;

    @Autowired
    RedisMapService redisMapService;

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public ResponseResult getUserList(String name) throws OperationException {
        String url = "/user/getList";
        Map param = new HashMap();
        param.put("pageSize", 2);
        param.put("pageNo", 1);
        param.put("name", name);
        param.get("aaa").toString();
        if (1 == 1) {
            throw new OperationException(21, "test throw globle");
        }
       return authHttpService.get(url, param);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult updateUser(@RequestParam Map param) throws Exception{
        String url = "/user/update";
        return this.authHttpService.post(url,param);

    }

    @RequestMapping(value = "/redis/set", method = RequestMethod.POST)
    public ResponseResult set(@RequestParam Map param) throws Exception{
        CurrentUser currentUser = new CurrentUser();
        currentUser.setLoginName("战三");
        currentUser.setMobile("333333");
        redisMapService.set("redisGetTest", currentUser,1800);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");

    }

    @RequestMapping(value = "/redis/get", method = RequestMethod.POST)
    public ResponseResult get(@RequestParam Map param) throws Exception{
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, redisMapService.get("redisGetTest"));
    }

    @RequestMapping(value = "/redis/setExpire", method = RequestMethod.GET)
    public ResponseResult setExpire(@RequestParam Map param) throws Exception{
        this.redisMapService.setExpire("redisGetTest", 1800);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");
    }

    @RequestMapping(value = "/redis/delete", method = RequestMethod.POST)
    public ResponseResult delete(@RequestParam Map param) throws Exception{
        redisMapService.delete("redisGetTest");
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");
    }

}
