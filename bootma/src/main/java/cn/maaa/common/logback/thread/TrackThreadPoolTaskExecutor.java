package cn.maaa.common.logback.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * TrackThreadPoolTaskExecutor
 * @author mazh
 * @date 2019年06月18日 14:26 
 */

/**
 * 将ThreadPoolTaskExecutor替换成TrackThreadPoolTaskExecutor
 */
public class TrackThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

	@Override
	public void execute(Runnable runnable) {
		super.execute(new RunnableWrapper() {
			@Override
			public void trackRun() {
				runnable.run();
			}
		});
	}
}
