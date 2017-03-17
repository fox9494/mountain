package org.chenll.thread;

import java.util.concurrent.Callable;

/**
 * Created by chenlile on 17-3-10.
 */
public class WaitNotifyTest {

    public static void main(String[] args) {

        final Inventory inventory = new Inventory();



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        inventory.addCount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    inventory.disCount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }



    private static class Inventory{

        private Long count=0L;

        boolean wasSignalled = true;//设置信号标志，防止wait自动苏醒


        public void disCount() throws InterruptedException {
            synchronized (this){
                while(true){
                    if (count==0 ){
                        System.err.println("开始消费等待");
                        wasSignalled = false;
                        this.wait();//只有有锁的情况调用wait
                    }
                    if (wasSignalled){ //wait有可能自动苏醒，所以要加wasSignalled进行判断
                        count--;
                        System.out.println("我在消费,count = " + count);
                        Thread.sleep(500);

                    }

                }
            }

        }


        public void addCount() throws InterruptedException {

            while(true){
             synchronized (this){

                    if (count>10){//库存大于50,可以消费了
                        System.err.println("开始通知可以消费");
                        wasSignalled=true;
                        //通知后，继续执行余下代码，只有放弃该锁或执行完同步块，继续和唤醒线程竞争
                        this.notify();//只有有锁的情况调用wait
                    }
                    count++;
                    System.out.println("我在生产,count = " + count);
                    Thread.sleep(500);

                }

            }

        }
    }
}
