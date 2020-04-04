package rt.java.util.concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();

    public void test() {
        Thread thread1 = new Thread("1号") {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    lock();
                }
            }
        };
        Thread thread2 = new Thread("2号") {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    lock();
                }
            }
        };
        thread1.start();
        thread2.start();
    }

    public void lock() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        reentrantLockDemo.test();
    }
}
