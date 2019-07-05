package cn.maaa.common.logback.thread;


import cn.maaa.common.logback.cnostant.TrackConsts;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * CallableWrapper
 * @author mazh
 * @date 2019年07月01日 16:16 
 */
public abstract class CallableWrapper<V> implements Callable<V> {

	public abstract  V trackCall() throws Exception;

	Map<String, String> context = MDC.getCopyOfContextMap();

	@Override
	public V call() throws Exception{
		try {
			//非请求触发 不经过LogInterceptor获取不到context(如自动调度)，做默认不追踪标记
			if(null == context){
				MDC.put(TrackConsts.TRACK_KEY, TrackConsts.NON_TRACK_VALUE);
				context = MDC.getCopyOfContextMap();
			}
			MDC.setContextMap(context);
			return trackCall();
		}finally {
			MDC.clear();
		}
	}
}
