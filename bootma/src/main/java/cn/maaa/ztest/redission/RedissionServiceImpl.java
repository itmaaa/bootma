package cn.maaa.ztest.redission;

import cn.maaa.common.annotation.RedissonLock;
import cn.maaa.system.domain.User;
import cn.maaa.system.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private AtomicInteger number = new AtomicInteger(1);

	//@Transactional
	@Override
    //@RedissonLock(keys = "testKey")
	public void saveUser() {
		int num = number.getAndIncrement();
		if(num % 51 == 0 ){
			try {
				Thread.sleep(40000L);
				System.out.println("num:"+num + "--睡眠结束...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		userMapper.insert(new User().setUsername("user-" + num ));
		Integer total = userMapper.selectCount(null);
		log.info("total user number is {}", total);

	}

	@Override
	@RedissonLock(keys = "# user.id")
	public void saveUser(User user) {
		int num = number.getAndIncrement();
		if(num % 51 == 0 ){
			try {
				Thread.sleep(40000L);
				System.out.println("num:"+num + "--睡眠结束...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		userMapper.insert(new User().setUsername("user-" + num ));
		Integer total = userMapper.selectCount(null);
		log.info("total user number is {}", total);
	}
}
