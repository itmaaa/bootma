package cn.maaa.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CyclicBarrierTest
 * @author mazh
 * @date 2019年07月05日 16:37 
 */
@Slf4j
public class CyclicBarrierTest {


	public static void main(String[] args) {
		//ride();
		//doAsCountDownLatch();
		doAsSemaphore();
	}



    /**
     * 100任务，分10次执行
	 * 模拟信号量限流
     */
	public static void  doAsSemaphore(){

		AtomicInteger count = new AtomicInteger(0);

		ExecutorService executor = Executors.newCachedThreadPool();

		final CyclicBarrier cyclicBarrier = new CyclicBarrier(11,()->{
			int times = count.incrementAndGet();
			log.info("第{}批任务执行完毕，准备开始下一批任务...",times);

		});

		retry:
		for (int i =1 ;i<=10 ;i++){
			executor.execute(()->{
				try {
					Thread.sleep(5000);
					log.info("当前线程:{}",Thread.currentThread().getName());
					cyclicBarrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			if(i % 10 == 0){
				try {
					cyclicBarrier.await();
					if(count.get() != 10)
						i = 0;
					continue retry;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		log.info("========结束了=========");
		executor.shutdown();
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
		executor.shutdown();
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
