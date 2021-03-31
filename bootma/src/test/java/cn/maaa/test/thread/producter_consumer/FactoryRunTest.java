package cn.maaa.test.thread.producter_consumer;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/3/30 15:49
 */
public class FactoryRunTest {

    public static void main(String[] args) {
       /** 同一时刻只有一条线程在工作，生产或消费
        该模式不一定等生产量达到队列最大值才开始消费
        也不一定等队列消费完才继续生产*/
        LinkedList<Integer> queue = new LinkedList<>();
        AtomicInteger currentNo = new AtomicInteger(0);
        new Thread(new SyncProduter(queue, "生产者1",currentNo)).start();
        new Thread(new SyncProduter(queue, "生产者2",currentNo)).start();
        new Thread(new SyncConsumer(queue,"消费者1" )).start();
        new Thread(new SyncConsumer(queue,"消费者2" )).start();
    }
}
