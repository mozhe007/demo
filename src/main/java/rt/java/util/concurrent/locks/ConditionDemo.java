package rt.java.util.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/*object的方法关键点在于 当前线程和对象的监听器，调用此方法，会释放当前线程对于此对象的监听器，当前线程也处于等待 阻塞状态,前提是当前线程已经获取了CPU的的监听器，

        condition await()  旨在当前线程的调度，与对象无关，调用此方法会是当前线程释放LOCK，并且当前线程会进行condition的等待队列，等待有其他condition调用dosignal（）
        会被唤醒，将当前线程重新放入同步队列中等待获取锁，

        两种在表现上有点相似，但是作用机制是有区别的
        1.object wait() 不能单独使用，必须是在synchronized 下才能使用，
        2.object wait()必须要通过Nodify()方法进行唤醒
        3.condition await() 必须是当前线程被排斥锁 lock 后,，获取到condition 后才能使用
        4.condition await() 必须通过 sign() 方法进行唤醒

        一个是基于对象监听器的同步方式，一个是基于 ASQ同步机制的同步方式*/

public class ConditionDemo {
    /*newCondition 产生的 ConditionObject 是AbstractQueuedSynchronizer （AQS）的内部类*/
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void await() {
        try {
            condition.await();


            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // wrong case
    //  throw IllegalMonitorStateException
    public void awaitWithoutLock(){
        lock.unlock();

        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // 看文章， 这个  await signal 的最大体现是生产者和消费者模式， 所以以火车票为例子写一个。
    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();
        conditionDemo.awaitWithoutLock();
    }
}
