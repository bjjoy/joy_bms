package cn.bjjoy.web.bms.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author bjjoy
 * @date 2018/3/24
 **/
@Service
public class RedisSortedSetService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void zAdd(String key, Object value, double score){
        ZSetOperations<String, Object> operations = redisTemplate.opsForZSet();
        operations.add(key, value, score);
    }

    public void incrementScore(String key, String value, double score){
        ZSetOperations<String, Object> operations = redisTemplate.opsForZSet();
        operations.incrementScore(key, value, score);
    }

    public Set<Object> range(String key, long start, long end){
        ZSetOperations<String, Object> operations = redisTemplate.opsForZSet();
        return operations.range(key, start, end);
    }

    /**
     * 反向获取数据集合value，score的键值对
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseRangeWithScore(String key, long start, long end){
        ZSetOperations<String, Object> operations = redisTemplate.opsForZSet();
        return operations.reverseRangeWithScores(key, start, end);
    }
}
