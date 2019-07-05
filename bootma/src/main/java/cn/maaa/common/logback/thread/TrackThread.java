package cn.maaa.common.logback.thread;

/**
 * TrackThread
 * @author mazh
 * @date 2019年06月14日 17:28 
 */


/**
 * 将Thread替换成TrackThread
 */
public class TrackThread extends Thread{

	public TrackThread(Runnable target) {
		super(new RunnableWrapper() {
			@Override
			public void trackRun() {
				target.run();
			}
		});
	}

}


