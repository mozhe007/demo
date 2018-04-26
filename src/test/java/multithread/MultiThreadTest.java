package multithread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiThreadTest {

    @Test
    public void WaitTest() throws InterruptedException {
        System.out.println("main begin...");
        MultiThread.Wait w = new MultiThread.Wait();
        new Thread(w).start();
        Thread.sleep(2000);//为了保证执行w.wait()在w.notify()之前
        synchronized (w) {
            w.notify();
        }
        System.out.println("main end...");
    }

    @Test
    public void SleepTest() {
        System.out.println("main begin...");
        MultiThread.Sleep w = new MultiThread.Sleep();
        new Thread(w).start();
        System.out.println("main end...");

    }

    /* 生产 消费者模式 */
    public static void main(String[] args) {
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        MultiThread.Provider p1 = new MultiThread.Provider(queue,1);
        MultiThread.Provider p2 = new MultiThread.Provider(queue,2);
        MultiThread.Customer c1 = new MultiThread.Customer(queue,1);
        MultiThread.Customer c2 = new MultiThread.Customer(queue,2);
        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }

    @Test
    public void countdownLatch(){
        MultiThread multiThread = new MultiThread();
        multiThread.countdownLatch();
    }
}
