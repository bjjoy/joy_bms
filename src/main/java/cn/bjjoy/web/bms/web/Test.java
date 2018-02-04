package cn.bjjoy.web.bms.web;

import cn.bjjoy.web.bms.auth.service.CurrentUser;
import cn.bjjoy.web.bms.auth.service.AuthUserService;
import cn.bjjoy.web.bms.base.ResponseCode;
import cn.bjjoy.web.bms.base.ResponseResult;
import cn.bjjoy.web.bms.exception.OperationException;
import cn.bjjoy.web.bms.redis.RedisMapService;
import cn.bjjoy.web.bms.http.service.AuthHttpService;
import cn.bjjoy.web.bms.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/9
 **/
@RestController
@RequestMapping("/test")
public class Test {

    @Autowired
    AuthHttpService authHttpService;

    @Autowired
    RedisMapService redisMapService;

    @Autowired
    AuthUserService authUserService;

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

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public ResponseResult getCurrentUser(@RequestParam Map param) throws Exception{
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, authUserService.getCurrentUser());

    }

    @RequestMapping(value = "/redis/set", method = RequestMethod.POST)
    public ResponseResult set(@RequestParam Map param) throws Exception{
        CurrentUser currentUser = new CurrentUser();
        currentUser.setLoginName("战三");
        currentUser.setMobile("333333");
        redisMapService.set("redisGetTest", currentUser,1800);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");

    }

    @RequestMapping(value = "/redis/get", method = RequestMethod.GET)
    public ResponseResult get(@RequestParam Map param) throws Exception{
        CurrentUser user = DataUtils.getData(redisMapService.get("redisGetTest"), CurrentUser.class);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, user);
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

    @RequestMapping(value = "/geta", method = RequestMethod.GET)
    public ResponseResult geta() throws Exception{
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, authUserService.getA());
    }

    @RequestMapping(value = "/seta", method = RequestMethod.GET)
    public ResponseResult seta(@RequestParam("a") String a) throws Exception{
        authUserService.setA(a);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, a);
    }
}
