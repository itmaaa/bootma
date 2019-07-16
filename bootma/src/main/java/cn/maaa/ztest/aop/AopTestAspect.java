package cn.maaa.ztest.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * xxx 
 * @author mazh
 * @date 2019年03月27日 9:54 
 */
@Component
@Aspect
public class AopTestAspect {

	@Pointcut("@annotation(cn.maaa.ztest.aop.AopTest)")
	public void logPointCut() { }

	@Before("logPointCut()")
	public void before(){
		System.out.println("before执行...");
	}

	@After("logPointCut()")
	public void after(){
		System.out.println("after执行...");
	}
}
