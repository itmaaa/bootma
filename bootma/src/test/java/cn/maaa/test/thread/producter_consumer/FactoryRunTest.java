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
        LinkedList<Integer> queue = new LinkedList<>();
        AtomicInteger currentNo = new AtomicInteger(0);
        new Thread(new SyncProduter(queue, "生产者1",currentNo)).start();
        new Thread(new SyncProduter(queue, "生产者2",currentNo)).start();
        new Thread(new SyncConsumer(queue,"消费者1" )).start();
        new Thread(new SyncConsumer(queue,"消费者2" )).start();
    }
}
