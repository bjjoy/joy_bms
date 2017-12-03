package cn.bjjoy.web.joy_bms.web;

import cn.bjjoy.web.joy_bms.base.ResponseCode;
import cn.bjjoy.web.joy_bms.base.ResponseResult;
import cn.bjjoy.web.joy_bms.constants.URL;
import cn.bjjoy.web.joy_bms.constants.UserConstant;
import cn.bjjoy.web.joy_bms.dto.UserDto;
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
 * Created by GXM on 2017/11/25
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthHttpService authHttpService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseResult login(String loginName, String password) throws OperationException{

        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
            return new ResponseResult(UserConstant.LOGIN_PARAM_LACK, UserConstant.LOGIN_PARAM_LACK_MSG);
        }

        //redis获取用户信息，存在直接登录

        //redis不存在，查询数据库
        Map param = new HashMap();
        param.put("loginName", loginName);
        param.put("password", EncryptUtils.encryptMD5(password));
        ResponseResult responseResult = this.authHttpService.post(URL.USER_LOGIN, param);
        if (responseResult.getCode() != 200){
            return responseResult;
        }
        UserDto userDto = DataUtils.getData(responseResult.getData(), UserDto.class);

        //写入redis

        return new ResponseResult(ResponseCode.OK, ResponseCode.OK_TEXT);
    }
}
