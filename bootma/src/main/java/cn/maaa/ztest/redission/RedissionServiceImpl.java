package cn.maaa.ztest.redission;

import cn.maaa.system.domain.User;
import cn.maaa.system.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * xxx 
 * @author mazh
 * @date 2019年03月27日 9:42 
 */

@Service
@Slf4j
public class RedissionServiceImpl implements RedissionService {

	@Autowired
	private UserMapper userMapper;

	private AtomicInteger number = new AtomicInteger();

	private final String key = "I_AM_KEY";

	@Autowired
    private RedissonClient redissonClient;

	@Override
	public void saveUser() {
		RBucket<Object> bucket = redissonClient.getBucket(key);
		Object o = bucket.get();
		System.out.println("oldObject: "+ o);
		bucket.set("maaa");
		Object n  = bucket.get();
		System.out.println("newObject: " + n);

		RLock lock = redissonClient.getLock(key);
		try {
			lock.lock( );
			saveAndSelect();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	@Transactional
	public void saveAndSelect(){
		userMapper.insert(new User().setUsername("user-" + number.getAndIncrement()));
		Integer total = userMapper.selectCount(null);
		log.info("total user number is {}", total);
	}
}
