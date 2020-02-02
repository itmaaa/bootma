package cn.maaa.common.aspect;

import cn.maaa.common.annotation.Route;
import cn.maaa.common.utils.*;
import cn.maaa.system.domain.Log;
import cn.maaa.system.domain.User;
import cn.maaa.system.service.LogService;
import com.xiaoleilu.hutool.date.BetweenFormater;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 日志切面
 * @author mazh
 * @date 2019年02月26日 11:06 
 */
//@Component
//@Aspect
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Autowired
	private LogService logService;

	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Pointcut("@annotation(cn.maaa.common.annotation.Route)")
	public void logPointCut() { }

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//开启新线程之前，将RequestAttributes对象设置为子线程共享
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		RequestContextHolder.setRequestAttributes(sra, true);
		//异步保存日志
		//executorService.execute(()-> {
			saveLog(point, time);
		//});

		return result;
	}

	void saveLog(ProceedingJoinPoint joinPoint, long time) {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Log log = new Log();
		Route route = method.getAnnotation(Route.class);
		if (route != null) {
			// 注解上的描述
			log.setOperation(route.value());
		}
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		log.setMethod(className + "." + methodName + "()");
		// 请求的参数
		List<Object> args = Arrays.stream(joinPoint.getArgs()).filter(a -> !(a instanceof Model)).collect(Collectors.toList());

		if(args.size() > 0 ){
			try {
				String params = JsonUtils.beanToJson(args);
				if(params.length() > 999)
					params = StringUtils.left(params,996)+"...";
				log.setParams(params);
			} catch (Exception e) {
				logger.error("日志参数设置异常：{}", ExceptionUtils.errorMsg(e));
			}
		}
		// 获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		// 设置IP地址
		log.setIp(IPUtils.getIpAddr(request));
		// 用户名
		User currUser = ShiroUtils.getUser();
		if (null == currUser) {
				log.setUserId(-1L);
				log.setUsername("获取用户信息为空");
		} else {
			log.setUserId(ShiroUtils.getUserId());
			log.setUsername(ShiroUtils.getUser().getUsername());
		}
		log.setTime(DateUtil.formatBetween(time, BetweenFormater.Level.MILLSECOND));
		// 系统当前时间
		log.setGmtCreate(new Date());
		// 保存系统日志
		logService.save(log);
	}
}
