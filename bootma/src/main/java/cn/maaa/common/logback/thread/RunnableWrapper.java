package cn.maaa.common.logback.thread;


import cn.maaa.common.logback.cnostant.TrackConsts;
import org.slf4j.MDC;

import java.util.Map;

/**
 * RunnableWrapper
 * @author mazh
 * @date 2019年06月18日 9:13 
 */
public abstract class RunnableWrapper implements Runnable {

	public abstract void trackRun();

	Map<String, String> context = MDC.getCopyOfContextMap();

	@Override
	public void run() {
		try {
			//非请求触发 不经过LogInterceptor获取不到context(如自动调度)，做默认不追踪标记
            if(null == context){
				MDC.put(TrackConsts.TRACK_KEY, TrackConsts.NON_TRACK_VALUE);
				context = MDC.getCopyOfContextMap();
			}
			MDC.setContextMap(context);
			trackRun();
		}finally {
			MDC.clear();
		}
	}


}
