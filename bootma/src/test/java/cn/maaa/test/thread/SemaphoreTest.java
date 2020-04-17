package cn.maaa.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量测试
 * @author mazh
 * @date 2019年07月03日 16:31 
 */

/**
 *   在项目实际应用中，由于下载文件内容都比较大，如果同时有很多用户同时在下载，JVM的内存就会升的很高，甚至崩溃。
 *   为了避免很多用户同时下载，特引入Semaphore控制一次最多有配置个线程能进入实际下载的代码，即而控制JVM内存不会升的很高而导致崩溃。
 */
public class SemaphoreTest {
	public static void main(String[] args) {
		//线程池跑任务
		poolRun();
		//信号量限流
		//semaphoreRun();
	}

    /**
     * 限流后允许一次只能开两条线程处理任务
	 * 可以使用缓存线程池，每执行一次过后线程会回收，与poolRun()一样的效果
     */
	public static void semaphoreRun(){
		Semaphore semaphore = new Semaphore(2);
		for (int i = 0;i<10;i++){
			new Thread(() ->{
				try {
					semaphore.acquire();
					Thread.sleep(1000l);
				    System.out.println(Thread.currentThread().getName()+"--输出");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			}).start();

		}
	}

	/**
	 * 固定数量2的线程池一次只能处理两个任务
	 * 只有等任务处理完线程回收后才能继续后续的任务
	 */
	// private volatile int corePoolSize; FixedThreadPool用corePoolSize记录当前可用的线程数，队列
	public static void poolRun(){
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		for (int i = 0;i<10;i++){
			executorService.submit(() ->{
					try {
						Thread.sleep(1000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				    System.out.println(Thread.currentThread().getName()+"--输出");
					}

			);
		}

		executorService.shutdown();
	}


}
