package cn.maaa.test.thread.producter_consumer;

import cn.hutool.core.util.RandomUtil;
import java.util.Queue;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/3/30 15:39
 */
public abstract class Consumer implements Runnable{

    protected Queue<Integer> queue;

    Boolean empty(){
        return queue.size() == 0;
    }

}

 class SyncConsumer extends Consumer{

    private String name;

    public SyncConsumer(Queue<Integer> queue,String name) {
        super.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        for (;;){
            synchronized (queue){
                while (empty()){  // 有可能是被其他消费者唤醒，此时队列可能为空，继续等待
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer currentNo = queue.remove();
                System.out.println(String.format("%s消费第%s件商品",name,currentNo));
                queue.notifyAll();
                try {
                    Thread.sleep(RandomUtil.randomInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
