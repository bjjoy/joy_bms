package cn.bjjoy.web.joy_bms.web;

import cn.bjjoy.web.joy_bms.auth.service.AuthUserService;
import cn.bjjoy.web.joy_bms.auth.service.CurrentUser;
import cn.bjjoy.web.joy_bms.base.ResponseCode;
import cn.bjjoy.web.joy_bms.base.ResponseResult;
import cn.bjjoy.web.joy_bms.constants.URL;
import cn.bjjoy.web.joy_bms.constants.AuthUserConstant;
import cn.bjjoy.web.joy_bms.exception.OperationException;
import cn.bjjoy.web.joy_bms.service.AuthHttpService;
import cn.bjjoy.web.joy_bms.util.DataUtils;
import cn.bjjoy.web.joy_bms.util.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/25
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthHttpService authHttpService;

    @Autowired
    private AuthUserService authUserService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseResult login(String loginName, String password) throws OperationException{

        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
            return new ResponseResult(AuthUserConstant.LOGIN_PARAM_LACK, AuthUserConstant.LOGIN_PARAM_LACK_MSG);
        }

        //redis获取用户信息，存在直接登录
        CurrentUser currentUser = authUserService.getCurrentUser();
        if (currentUser != null) {
            return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, currentUser);
        }

        //redis不存在，查询数据库
        Map param = new HashMap();
        param.put("loginName", loginName);
        param.put("password", EncryptUtils.encryptMD5(password));
        ResponseResult responseResult = this.authHttpService.post(URL.USER_LOGIN, param);
        if (responseResult.getCode() != 200){
            return responseResult;
        }
        currentUser = DataUtils.getData(responseResult.getData(), CurrentUser.class);

        //写入redis
        authUserService.putUserToRedis(currentUser);
        return new ResponseResult(ResponseCode.OK, ResponseCode.OK_TEXT);
    }

    /**
     * 用户未登录或超时
     * @return 未登录异常
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ResponseResult error() throws OperationException{
        throw new OperationException(AuthUserConstant.OVERTIME, AuthUserConstant.OVERTIME_MSG);
    }
}
