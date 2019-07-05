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

	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10,()-> log.info("鸣枪开跑！！！" ));

	public static void main(String[] args) {
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
