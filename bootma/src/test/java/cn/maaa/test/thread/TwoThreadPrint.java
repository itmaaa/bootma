package cn.maaa.test.thread;

/**
 *   两个线程交替打印0~100的奇偶数：
 *      偶线程：0
 *      奇线程：1
 *      偶线程：2
 *      奇线程：3
 * @author :  mazh
 * @date :  2020/1/14 14:07
 */
public class TwoThreadPrint {

    static class SoulutionTask implements Runnable{
        static int value = 0;
        @Override
        public void run() {
            while (value <= 100){
                synchronized (SoulutionTask.class){
                    System.out.println(Thread.currentThread().getName() + ":" + value++);
                    SoulutionTask.class.notify();
                    try {
                        if(value <= 100)
                             SoulutionTask.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        new Thread(new SoulutionTask(), "偶数").start();
        new Thread(new SoulutionTask(), "奇数").start();
    }
}
