package cn.maaa.ztest.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * xxx 
 * @author mazh
 * @date 2019年03月27日 9:42 
 */
@Transactional
@Service
public class TestServiceImpl implements TestService {


	@AopTest
	@Override
	public void methodOne() {
		System.out.println("方法一执行");
	}

	@Autowired
	private TestService self;


	@Override
	public void methodTwo() {
		/**
		 * 这里调用了methodOne()，虽然它被注解标识,但前置后置通知都没有执行
		 */
		//methodOne();
		/**
		 * 解决方法
		 */
		self.methodOne();
		System.out.println("方法二执行");


	}
}
