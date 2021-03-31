package cn.maaa.test.thread.producter_consumer;

import cn.hutool.core.util.RandomUtil;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/3/30 15:16
 */
public abstract class Producter implements Runnable{

   final Integer max = 100;
   protected Queue queue ;
   protected AtomicInteger currentNo;

   Boolean full(){
      return queue.size() == max;
   }

}

 class SyncProduter extends Producter{

   private String name;

   public SyncProduter(Queue<Integer> queue,String name,AtomicInteger currentNo) {
      super.queue = queue;
      this.name = name;
      this.currentNo = currentNo;
   }

   @Override
   public void run()  {
      for (;;){
         synchronized (queue){
            while (full()){  //有可能是被其他生产者唤醒，此时队列可能满的，继续等待
               try {
                  queue.wait();
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
            queue.add(currentNo.incrementAndGet());
            System.out.println(String.format("%s生产第%s件商品",name,currentNo.get()));
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
