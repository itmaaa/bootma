package cn.maaa.test.thread;

import java.util.concurrent.Semaphore;

/**
 * 通过N个线程顺序循环打印从0至100，如给定N=3则输出:
 * thread0: 0
 * thread1: 1
 * thread2: 2
 * thread0: 3
 * thread1: 4
 * .....
 * <p>
 * 在Java的多线程中提供了一些常用的同步器，在这个场景下比较适合于使用Semaphore，也就是信号量，
 * 我们上一个线程持有下一个线程的信号量，通过一个信号量数组将全部关联起来
 *
 * @author :  mazh
 * @date :  2020/1/14 14:25
 */
public class MultiThreadPrint {
    static int result = 0;

    public static void main(String[] args) throws InterruptedException {
        int N = 3;
        Thread[] threads = new Thread[N];
        final Semaphore[] syncObjects = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            syncObjects[i] = new Semaphore(1);
            if (i != N - 1) {
                syncObjects[i].acquire();
            }
        }
        for (int i = 0; i < N; i++) {
            final Semaphore lastSemphore = i == 0 ? syncObjects[N - 1] : syncObjects[i - 1];
            final Semaphore curSemphore = syncObjects[i];
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    while (true) {
                        lastSemphore.acquire();
                        System.out.println("thread" + index + ": " + result++);
                        if (result > 100) {
                            System.exit(0);
                        }
                        curSemphore.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            threads[i].start();
        }
    }
}
