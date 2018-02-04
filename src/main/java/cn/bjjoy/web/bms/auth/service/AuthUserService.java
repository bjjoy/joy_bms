package cn.bjjoy.web.bms.auth.service;

import cn.bjjoy.web.bms.redis.RedisMapService;
import cn.bjjoy.web.bms.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bjjoy on 2017/11/28
 **/
@Service
public class AuthUserService {

    @Autowired
    RedisMapService redisMapService;

    private String a;
    /**
     * 获取当前sessionId对应用户信息
     * @return CurrentUser
     */
    public CurrentUser getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        CurrentUser currentUser = DataUtils.getData(redisMapService.get(sessionId), CurrentUser.class);
        return currentUser;
    }

    /**
     * 当前用户写入redis，sessionId作为key
     * @return CurrentUser
     */
    public void putUserToRedis(CurrentUser currentUser){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        redisMapService.set(sessionId, currentUser, 1800);
    }

    /**
     * 延长当前用户过期时间，sessionId作为key
     * @return CurrentUser
     */
    public void updateUserTime(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        redisMapService.setExpire(sessionId, 1800);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
