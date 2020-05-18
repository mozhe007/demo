package rt.java.util.concurrent;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.ReadWriteLock;

public class ThreadDemo {

    public void t (){
        Thread thread1 = new Thread(()->{
            System.out.println(1);
        });
        Thread thread2 = new Thread(()->{
            System.out.println("2");
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                }
        });
        Thread thread3 = new Thread(()->{
            System.out.println("3");
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();
        thread2.start();
        thread1.start();
    }

    /*
    而 Thread.interrupt 的作用其实也不是中断线程，而是「通知线程应该中断了」，
    具体到底中断还是继续运行，应该由被通知的线程自己处理。

    ② 如果线程处于正常活动状态，那么会将该线程的中断标志设置为 true，仅此而已。被设置中断标志的线程将继续正常运行，不受影响。
    */
    public void interrupt(){
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                // do more work.
                System.out.println("i am alive");
            }
            System.out.println(" i am dead ");
        });
        thread.start();
        // 过了一段时间
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 通知关闭
        thread.interrupt();
    }

    /* 如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），那么线程将立即退出被阻塞状态，
    并抛出一个InterruptedException异常。仅此而已。
    * */
    // java.lang.InterruptedException: sleep interrupted
    public void interrupt2(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        // 过了一段时间
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 通知关闭
        thread.interrupt();
    }

    /**
     * Waits for this thread to die.
     */
    public void join(){
        System.out.println(Thread.currentThread().getName() + "线程运行开始!");
        Thread thread1 = new Thread();
        thread1.setName("线程 B");
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这时 thread1 执行完毕之后才能执行主线程");
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.join();
    }
}
