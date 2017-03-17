package org.chenll.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenlile on 17-3-10.
 *
 *
 * ReentrantLock ：可重入的锁，即可以进入同一个锁对象的n个嵌套方法,和synchronized一样
 *
 *
 * ReentrantLock:满足多条件的锁定和释放，可以成为公平锁（等待最长最先得到）,synchronized非公平
 */
public class ReentrantLockTest {


    public static void main(String[] args) {
        final Person person = new Person();

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                person.showName();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person.showAge();
            }
        }).start();*/


        new Thread(new Runnable() {
            @Override
            public void run() {
                person.consumer();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person.product();
            }
        }).start();
    }





    private static class Person{

        private ReentrantLock lock = new ReentrantLock();

        private Condition conditon = lock.newCondition();

        private long count=0;


        public void showName()  {//进入同一个对象锁的另一个方法
            lock.lock();
            System.out.println("this is showName");
            lock.lock();
            System.out.println("this is showName2");
            lock.unlock();

            System.out.println("this is showName2 over");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            lock.lock();
            int k = lock.getHoldCount();
            System.out.println("this is k:"+k);
            lock.unlock();
            System.out.println("this is showName over");
        }

        public void showAge(){
            lock.lock();
            System.out.println("this is showAge");
            lock.unlock();
        }



        public void consumer(){
            while(true){
                lock.lock();
                if (count==0){
                    try {
                        conditon.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
                System.out.println("消费者count = " + count);

                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void product(){
            while(true){
                lock.lock();
                if (count>10){
                    try {
                        System.out.println("通知消费者 " );
                        conditon.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                count++;
                System.out.println("生产者count = " + count);
                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
