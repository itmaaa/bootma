package cn.maaa.ztest.redission;

import cn.maaa.system.domain.User;

/**
 * xxx 
 * @author mazh
 * @date 2019年03月27日 9:41 
 */
public interface RedissionService {

	void saveUser();

	void saveUser(User user);

	void getTickets();

	void test();
}
