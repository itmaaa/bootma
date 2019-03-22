package cn.maaa.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 环绕通知异常测试
 * @author mazh
 * @date 2019年03月22日 9:16 
 */
@Component
@Aspect
public class AspectTest {

	/**
	 * 相比之下，环绕通知如果point.proceed()执行前代码报错，则影响后面代码的执行
	 * 前置通知和后置通知无论point.proceed()方法执行是否异常都会执行，
	 * 前置通知如果异常会影响point.proceed()执行，所以得做tryCatch处理
	 */

	@Pointcut("execution(* cn.maaa.system.controller.TestController.*testFreeMarker(..))")
	public void pointCut(){}

	//@Around("pointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("===========切面开始=============");
		before();
		//方法执行出现异常,after不执行
		Object result = point.proceed();
		after();
		return result;
	}

	public void before(){
		System.out.println("===========before 执行=============");
		//throw new RuntimeException("before执行异常");
		//抛出异常，后续代码不执行
	}

	public void after(){
		System.out.println("===========after 执行=============");
		//throw new RuntimeException("after执行异常");
		//抛出异常，point.proceed()执行完
	}

	//point.proceed()执行异常,beforeAdv,AfterAdv都会执行
    @Before("pointCut()")
	public void beforeAdv(){
			System.out.println("=================前置通知beforeAdv执行================");
			// throw new RuntimeException("beforeAdv执行异常");
		  //point.proceed()不执行,AfterAdv执行，所以beforeAdv得做tryCatch
	}

	/*@After("pointCut()")
	public void AfterAdv(){
		System.out.println("=================后置通知AfterAdv执行================");
		//throw new RuntimeException("AfterAdv执行异常");
		//抛出异常，point.proceed()执行完
	}*/


	/**
	   * 方法正常结束后执行的代码
	   * 返回通知是可以访问到方法的返回值的
	   */
     @AfterReturning(value="pointCut()", returning="result")
     public void afterReturning(JoinPoint joinPoint, Object result) {
		 System.out.println("=================afterReturning执行================");
		 String methodName = joinPoint.getSignature().getName();
		 System.out.println("The method " + methodName + " return with " + result);
	 }

	 /**
       * 在方法出现异常时会执行的代码
       * 可以访问到异常对象，可以指定在出现特定异常时在执行通知代码
       */
	 @AfterThrowing(value="pointCut()", throwing="ex")
	 public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		 System.out.println("=================afterThrowing执行================");
		 String methodName = joinPoint.getSignature().getName();
		 System.out.println("The method " + methodName + " occurs exception: " + ex);
	 }


}
