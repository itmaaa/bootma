package cn.maaa.ztest.redission;

import cn.maaa.common.utils.M;
import cn.maaa.system.domain.User;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * @author :  mazh
 * @date :  2019/11/13 17:22
 */
@Controller
public class RedissionController {

    @Autowired
    private RedissionService testService;

    private final String key = "I_AM_KEY";

    @Autowired
    private RedissonClient redissonClient;

    /** 查询 */
    @GetMapping("/test/redisson")
    @ResponseBody
    public M testFreeMarker(Model model){
       /* RBucket<Object> bucket = redissonClient.getBucket(key);
        Object o = bucket.get();
        System.out.println("oldObject: "+ o);
        bucket.set("maaa");
        Object n  = bucket.get();
        System.out.println("newObject: " + n);*/

       //测试加锁情况
		/*RLock lock = redissonClient.getLock(key);
		try {
		    // key超过30s解锁了
			//lock.lock( 30000L, TimeUnit.MILLISECONDS);
            //leaseTime为-1L表示永不删除，但实际设置过期时间为30,再每隔10s不断续约
            lock.lock();
            testService.saveUser();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}*/

		//若测试不加锁情况 去除方法上@RedissonLock注解
        //testService.saveUser();
       // User user = new User().setId(100L);
        //testService.saveUser(user);
        testService.getTickets();
        return M.ok();
    }
}
