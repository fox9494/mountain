package org.chenll.thread;

/**
 * Created by chenlile on 17-3-9.
 * synchronized各种测试
 *
 * synchronized对于对象的锁和类锁是不同的，静态方法加是类锁
 */
public class synchronizedTest {


    public static void main(String[] args) throws InterruptedException {

        final Person person = new Person();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始第二个线程" );
                person.showName();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始第一个线程" );
                person.showAge();
            }
        }).start();

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始第三个线程" );
                Person.showBirth();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始第四个线程" );
                Person.showWeight();
            }
        }).start();*/



    }



    public static class Person{

        private Object obj = new Object();


        public void showName(){//等所有嵌套执行完，第二个线程才能执行showAge
            synchronized (this){//加的对象锁
                System.out.println(" this is show Name" );
                synchronized (this){
                    System.out.println(" this is show Name2" );
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(" this is show Name2 over" );
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" this is show Name over" );
            }
        }

        //加的是对象锁，但是我showname是不一样的锁
        public void showAge(){
            synchronized (this){
                System.out.println(" this is show Age" );
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        /**
         * 加的类锁
         */
        public static synchronized void showBirth(){
            System.out.println(" this is show Birth" );
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        /**
         * 加的类锁
         */
        public static synchronized void showWeight(){
            System.out.println(" this is show Weight" );
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
