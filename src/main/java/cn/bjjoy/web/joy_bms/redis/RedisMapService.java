package cn.bjjoy.web.joy_bms.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by GXM on 2017/12/3
 **/
@Service
public class RedisMapService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key, Object object){
        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
        operations.set(key, object);
    }

    public void set(String key, Object object, long seconds){
        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
        operations.set(key, object, seconds, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public Object get(String key){
        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
        return operations.get(key);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public void setExpire(String key, long seconds){
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
}
