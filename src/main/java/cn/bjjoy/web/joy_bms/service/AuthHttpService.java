package cn.bjjoy.web.joy_bms.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by bjjoy on 2017/11/13
 **/
@Service
@ConfigurationProperties(prefix = "joy.service")
public class AuthHttpService extends BaseHttpService{

    private String auth = "http://localhost:8010";

    @Override
    public String getDomain() {
        return auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
