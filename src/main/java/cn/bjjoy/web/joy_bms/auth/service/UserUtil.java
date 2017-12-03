package cn.bjjoy.web.joy_bms.auth.service;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by GXM on 2017/11/28
 **/
public class UserUtil {

    public static CurrentUser getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        CurrentUser currentUser = new CurrentUser();
        return currentUser;
    }
}
