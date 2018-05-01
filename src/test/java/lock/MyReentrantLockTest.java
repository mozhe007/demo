package lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class MyReentrantLockTest {

    @Test
    public void lock() {
        MyBean myBean = new MyBean(1, 500);
        MyReentrantLock lock = new MyReentrantLock();
        lock.lock1(myBean);
        lock.lock2(myBean); // 同线程同对象可以获取锁
        OtherObject otherObject = new OtherObject();
        otherObject.lock1(myBean); // 同线程同对象可以获取锁
        OtherThread myThread = new OtherThread(myBean);
        new Thread(myThread).start(); //因为lock 不同线程 不可以获取锁, 会一直阻塞直到拿到锁
        lock.unlock(myBean); // getHoldCount 3==》2
        lock.unlock(myBean); // getHoldCount 2==》1
        lock.unlock(myBean); // getHoldCount 1==》0 此时  new Thread(myThread).start();的run()方法开始运行
        //lock.unlock(myBean); // 这里会报错，因为并没有lock是做了unlock操作。
    }

    @Test
    public void tryLock() {
        MyBean myBean = new MyBean(1, 500);
        MyReentrantLock lock = new MyReentrantLock();
        lock.lock1(myBean);
        lock.tryLock(myBean);// 可以拿到
        lock.unlock(myBean);// getHoldCount 2==》1
        lock.unlock(myBean);// getHoldCount 1==》0

        OtherThread myThread = new OtherThread(myBean);
        new Thread(myThread).start(); // 别的线程获取到锁
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.tryLock(myBean);// 不可以拿到
    }

    @Test
    public void lockInterruptibly() {
        MyBean myBean = new MyBean(1, 500);
        MyReentrantLock lock = new MyReentrantLock();
        lock.lockInterruptibly(myBean); // 可以获取到锁


    }

    public static void main(String[] args) {
        MyBean myBean = new MyBean(1, 500);
        MyReentrantLock lock = new MyReentrantLock();
        lock.lockInterruptibly(myBean); // 可以获取到锁
        lock.unlock(myBean); // getHoldCount 1==》0


        lock.lock1(myBean); // 模拟锁住
        Thread otherStopThread = new Thread(new OtherStopThread(myBean));
        otherStopThread.start();// 线程获取到锁
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        otherStopThread.interrupt();// 可以被阻断
    }
}
