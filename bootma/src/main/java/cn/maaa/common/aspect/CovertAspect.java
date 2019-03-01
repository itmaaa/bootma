package cn.maaa.common.aspect;

import cn.maaa.common.annotation.Convert;
import cn.maaa.common.utils.ExceptionUtils;
import cn.maaa.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * bean复制切面
 * @author mazh
 * @date 2019年02月28日 11:08
 */
@Component
@Aspect
@Slf4j
public class CovertAspect {


	//@Pointcut("execution(* cn.maaa.common.utils.BeanConvert.execute(..))")
	//@Pointcut("execution(* cn.maaa.common.utils.BeanConvert.sayHello(..))")
	//public void covertPointCut() { }

	//@Around("covertPointCut()")
	//ProceedingJoinPoint is only supported for around advice


	@After("execution(* cn.maaa.common.utils.BeanConvert.*Exec(..))")
	public  void mapper(JoinPoint joinPoint) throws Throwable{
		Object[] args = joinPoint.getArgs();
		if(args.length != 0 ){
			try {
				Object source = args[0];
				Object target = args[1];
				Field[] field = source.getClass().getDeclaredFields();
				if(field != null){
					for(Field fie : field){
						if(fie.isAnnotationPresent(Convert.class)){
							fie.setAccessible(true);
							Convert annon = fie.getAnnotation(Convert.class);
							String value = annon.value();
							Field targetField = target.getClass().getDeclaredField(value);
                            targetField.setAccessible(true);
							//给字段重新赋值
							targetField.set(target, fie.get(source));
						}
					}
				}

			} catch (Exception e) {
				log.error("转换参数异常：{}", ExceptionUtils.errorMsg(e));
			}
		}
	}
}

