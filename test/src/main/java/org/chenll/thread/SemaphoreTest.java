package org.chenll.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by chenlile on 17-3-13.
 * Semaphore 通常用于限制并发的线程数目
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);


    private static Semaphore s = new Semaphore(10);//只允许10个线程并发

    public static void main(String[] args) {

        for (int i = 0; i < THREAD_COUNT; i++)
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println("thread ID:"+Thread.currentThread().getId()+"save data");

                        Thread.sleep(3000);
                        s.release();


                    } catch (InterruptedException e) {
                    }
                }
            });
        threadPool.shutdown();
    }
}








