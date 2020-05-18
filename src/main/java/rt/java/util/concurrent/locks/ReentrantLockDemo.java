package rt.java.util.concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

/*

当线程尝试获取AQS的锁时，如果AQS已经被别的线程获取锁，那么将会新建一个Node节点，
并且加入到AQS的等待队列中，这个队列也由AQS本身自己维护。当锁被释放时，唤醒下一个节点尝试获取锁。

ReentrantLock的基本实现可以概括为：先通过CAS尝试获取锁。如果此时已经有线程占据了锁，那就加入CLH队列并且被挂起。
当锁被释放之后，排在CLH队列队首的线程会被唤醒，然后CAS再次尝试获取锁。在这个时候，如果：
        非公平锁：如果同时还有另一个线程进来尝试获取，那么有可能会让这个线程抢先获取；
        公平锁：如果同时还有另一个线程进来尝试获取，当它发现自己不是在队首的话，就会排到队尾，由队首的线程获取到锁。
        */
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
