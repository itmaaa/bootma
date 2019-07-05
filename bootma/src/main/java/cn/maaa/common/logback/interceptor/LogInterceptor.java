package cn.maaa.common.logback.interceptor;


import cn.maaa.common.logback.cnostant.TrackConsts;
import cn.maaa.common.logback.utils.SnowflakeIdWorker;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LogInterceptor
 * @author mazh
 * @date 2019年06月14日 17:17 
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

	private static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1,1);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String header = request.getHeader(TrackConsts.TRACK_KEY);
		String trackKey = StringUtils.isEmpty(header) ? String.valueOf(snowflakeIdWorker.nextId()) : header;

		MDC.put(TrackConsts.TRACK_KEY, trackKey);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		MDC.remove(TrackConsts.TRACK_KEY);
	}
}
