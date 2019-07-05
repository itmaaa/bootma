package cn.maaa.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrierTest
 * @author mazh
 * @date 2019年07月05日 16:37 
 */
@Slf4j
public class CyclicBarrierTest {

	private static ExecutorService executor = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		//ride();
		//doAsCountDownLatch();
		doAsSemaphore();
	}




	public static void  doAsSemaphore(){
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

		for (int i =1 ;i<=100 ;i++){
			executor.execute(()->{
				try {
					Thread.sleep(5000);
					log.info("当前线程:{}",Thread.currentThread().getName());
					cyclicBarrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		}

	}


    /**
     * 模拟CountDownLatch
     */
	public static void doAsCountDownLatch(){
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i =1 ;i<10 ;i++){
			executor.execute(()->{
				try {
					Thread.sleep(1000);
					log.info("子线程执行..." );
					cyclicBarrier.await();
				} catch (Exception e) {
					log.warn("BarrierException", e);
				}

			});
		}
		try {
			cyclicBarrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("主线程线程执行..." );

	}

	/**
	 * 多线程相互等待
	 */
	private static void ride() {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(10,()-> log.info("鸣枪开跑！！！" ));

		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i =1 ;i<=10;i++){
			final int no = i;
			executor.execute(()->{
				try {
					Thread.sleep(1000);
					log.info("{} 号马已进入栅栏,准备就绪", no);
					cyclicBarrier.await();


					log.info("{} 号马已冲出栅栏", no);
				} catch (Exception e) {
					log.warn("BarrierException", e);
				}

			});


		}

		executor.shutdown();
	}


}
