package cn.bjjoy.web.bms;

import cn.bjjoy.web.bms.auth.service.CurrentUser;
import cn.bjjoy.web.bms.redis.RedisHashService;
import cn.bjjoy.web.bms.redis.RedisPojoService;
import cn.bjjoy.web.bms.redis.RedisSortedSetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JoyBmsApplicationTests {

	@Autowired
	RedisPojoService redisPojoService;

	@Autowired
	RedisHashService redisHashService;

	@Autowired
	RedisSortedSetService redisSortedSetService;

	@Test
	public void contextLoads() {
		CurrentUser a = new CurrentUser();
		a.setLoginName("123");
		a.setMobile("1234");
//		redisHashService.increment("test_key1", "map_key", 1);
//		redisHashService.put("test_key", "map_key", a);
//		redisPojoService.set("test_pojo",a);
//		redisPojoService.increment("test_222", 3);
//		redisSortedSetService.zAdd("ztest", a, 8);
//		redisSortedSetService.zAdd("ztest", "user2", 4);
		Set<Object> ztest = redisSortedSetService.range("ztest",0, -1);
		Set<ZSetOperations.TypedTuple<Object>> ztest2 = redisSortedSetService.reverseRangeWithScore("ztest",0, -1);
		for (ZSetOperations.TypedTuple<Object> tmp : ztest2){
			if (tmp.getValue() instanceof CurrentUser) {
				CurrentUser b = (CurrentUser) tmp.getValue();
				System.out.println(b);
			}
			System.out.println(tmp.getScore());
			System.out.println(tmp.getValue());
		}
		System.out.println(ztest.isEmpty());

	}

}
