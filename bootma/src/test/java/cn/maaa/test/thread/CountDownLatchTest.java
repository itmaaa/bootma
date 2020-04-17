package cn.maaa.test.thread;

import com.xiaoleilu.hutool.date.BetweenFormater;
import com.xiaoleilu.hutool.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CountDownLatchTest
 * @author mazh
 * @date 2019年07月04日 9:33 
 */
@Slf4j
public class CountDownLatchTest {

	public static void main(String[] args) throws Exception {
		testCallableTask();
		//testRunnableTask();
	}

	private static void testCallableTask() throws Exception {
		long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(50000);
		LinkedList<Future<Integer>> list = new LinkedList<>();
		int i = 1;

		//future.get()是阻塞操作，需要放到循环外，否则效率跟串行执行无差别
		for (;i <=50000;i++){
			Future<Integer> future = callableTask(executorService, i);
			//future.get()阻塞，串行获取结果
			//Integer integer = future.get();
			//log.info("输出结果{}" , integer);
			list.add(future);
		}

		//任务并行执行了，但get操作是阻塞的，输出结果始终是1,2,3,4,5
		for (Future<Integer> future: list) {
			log.info("输出结果{}" , future.get());
		}
		log.info("======任务耗时：{}=======", DateUtil.formatBetween(System.currentTimeMillis()-start, BetweenFormater.Level.MILLSECOND));
		executorService.shutdown();
	}


	public static Future<Integer>  callableTask(ExecutorService executorService,final int i){
		Future<Integer> future = executorService.submit(() -> {
			log.info("当前是第{}个任务执行", i);
			Thread.sleep(1000);
			return i;
		});

		return future;
	}


	private static void testRunnableTask() throws Exception {
		long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(50000);
		final List<Integer> list = Collections.synchronizedList(new LinkedList<>());
		int i = 1;

		CountDownLatch latch = new CountDownLatch(50000);

		for (;i <=50000;i++){
			runnableTask(executorService, i, list,latch);
		}

		latch.await();
		for (Integer result: list) {
			log.info("输出结果{}" , result);
		}
		log.info("======任务耗时：{}=======", DateUtil.formatBetween(System.currentTimeMillis()-start, BetweenFormater.Level.MILLSECOND));
		executorService.shutdown();
	}

	public static void runnableTask(ExecutorService executorService,final int i,List<Integer>list,CountDownLatch latch){
		 executorService.execute(()->{
			 log.info("当前是第{}个任务执行", i);
			 try {
				 Thread.sleep(1000);
			 } catch (InterruptedException e) {
				 e.printStackTrace();
			 }
			 list.add(i);
			 latch.countDown();
		 });
	}


}
