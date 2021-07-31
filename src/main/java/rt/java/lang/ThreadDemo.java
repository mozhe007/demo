package rt.java.lang;

import java.util.concurrent.Semaphore;

public class ThreadDemo {

    /**
     * 使用3个线程，1个线程打印x ，一个线程打印y，一个线程打印z ，同时执行连续打印10次
     * 利用join 方法，不断的新建
     */
    class MyThread extends Thread {
        private String id;

        public MyThread(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.print(id);
        }
    }

    public void myThread1Test() {
        for (int i = 0; i < 10; i++) {
            MyThread x = new MyThread("x");
            MyThread y = new MyThread("y");
            MyThread z = new MyThread("z");
            x.start();
            try {
                x.join();
                y.start();
                y.join();
                z.start();
                z.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 使用3个线程，1个线程打印x ，一个线程打印y，一个线程打印z ，同时执行连续打印10次
     * <p>
     * 利用 Semaphore 利好 ， 个人认为最好的方法。
     */
    class MyThread2 extends Thread {
        private String id;
        private Semaphore pre;
        private Semaphore self;

        public MyThread2(String id, Semaphore pre, Semaphore self) {
            this.id = id;
            this.pre = pre;
            this.self = self;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    pre.acquire();
                    System.out.print(id);
                    self.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void myThread2Test() {
        System.out.println("执行myThread2Test");
        Semaphore xClock = new Semaphore(1);
        Semaphore yClock = new Semaphore(1);
        Semaphore zClock = new Semaphore(1);

        MyThread2 x = new MyThread2("x", zClock, xClock);
        MyThread2 y = new MyThread2("y", xClock, yClock);
        MyThread2 z = new MyThread2("z", yClock, zClock);
        try {
            xClock.acquire();
            yClock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x.start();
        y.start();
        z.start();
    }

    /**
     * 使用3个线程，1个线程打印x ，一个线程打印y，一个线程打印z ，同时执行连续打印10次
     * 利用 wait  \notify 方法 通知线程
     */
    class MyThread3 extends Thread {
        private String id;
        private Thread next;

        public MyThread3(String id) {
            this.id = id;
        }

        public void setNext(Thread next) {
            this.next = next;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (this) {
                    try {
                        this.wait();
                        System.out.print(id);
                        synchronized (next) {
                            next.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void myThread3Test() {
        System.out.println("执行myThread3Test");

        MyThread3 x = new MyThread3("x");
        MyThread3 y = new MyThread3("y");
        MyThread3 z = new MyThread3("z");
        x.setNext(y);
        y.setNext(z);
        z.setNext(x);

        x.start();
        y.start();
        z.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (x) {
            x.notify();
        }
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.myThread3Test();
    }
}

