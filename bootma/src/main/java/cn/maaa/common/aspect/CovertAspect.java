package cn.maaa.common.aspect;

import cn.maaa.common.utils.ExceptionUtils;
import cn.maaa.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * xxx
 * @author mazh
 * @date 2019年02月28日 11:08
 */
@Component
@Aspect
@Slf4j
public class CovertAspect {


	//@Pointcut("execution(* cn.maaa.common.utils.BeanConvert.execute(..))")
	//@Pointcut("execution(* cn.maaa.common.utils.BeanConvert.sayHello(..))")
	//@Pointcut("execution(* cn.maaa.common.utils.BeanConvert.exec(..))")
	//public void covertPointCut() { }

	//@Around("covertPointCut()")
	//ProceedingJoinPoint is only supported for around advice


	@Before("execution(* cn.maaa.common.utils.BeanConvert.exec(..))")
	public  void before(JoinPoint joinPoint) throws Throwable{
		System.out.println("====================进入before=======================");
		Object[] args = joinPoint.getArgs();
		if(args.length != 0 ){
			try {
				String params = JsonUtils.beanToJson(args);
				System.out.println(params);
			} catch (Exception e) {
				log.error("转换参数异常：{}", ExceptionUtils.errorMsg(e));
			}
		}
	}
}
