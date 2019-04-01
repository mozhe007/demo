package rt.java.util.concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;
    public volatile static int ii = 0;

    @Override
    public void run() {
        volatileTest();
    }

    void lockTest(){
        for (int j = 0; j < 100000; j++) {
            lock.lock();
//            rt.java.util.concurrent.locks.lock.rt.java.util.concurrent.locks.lock();
            try {
                i++;
            } finally {
                lock.unlock();
//                rt.java.util.concurrent.locks.lock.unlock();
            }
        }
    }

    void volatileTest(){
        for (int j = 0; j < 100000; j++) {
            lock.lock();
//            rt.java.util.concurrent.locks.lock.rt.java.util.concurrent.locks.lock();
            try {
                i++;
                ii = i;
            } finally {
                lock.unlock();
//                rt.java.util.concurrent.locks.lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock2 reenterLock = new ReentrantLock2();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
        System.out.println(ii);
    }
}