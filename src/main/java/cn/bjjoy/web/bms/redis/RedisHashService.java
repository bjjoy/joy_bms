package cn.bjjoy.web.bms.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * @author bjjoy
 * @date 2018/3/24
 **/
@Service
public class RedisHashService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void put(String key, String mapKey, Object object){
        HashOperations<String, String, Object> operations = redisTemplate.opsForHash();
        operations.put(key, mapKey, object);
    }

    public long increment(String key, String mapKey, long i){
        HashOperations<String, String, Object> operations = redisTemplate.opsForHash();
        return operations.increment(key, mapKey, i);
    }
}
