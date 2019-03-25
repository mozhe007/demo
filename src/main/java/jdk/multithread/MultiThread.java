package jdk.multithread;

import java.util.Random;
import java.util.concurrent.*;

public class MultiThread {

    static class Wait implements Runnable {
        @Override
        public void run() {
            System.out.println("run");
            // 调用wait 必须锁定synchronized 此对象,不然IllegalMonitorStateException
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("after wait");
        }
    }

    static class Sleep implements Runnable {
        @Override
        public void run() {
            System.out.println("run");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after sleep");
        }
    }

    /* 生产 消费者模式 */
    static class Provider implements Runnable {
        private LinkedBlockingQueue queue;
        private int id;

        Provider(LinkedBlockingQueue queue, int id) {
            this.queue = queue;
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(1);
                System.out.println(" add " + queue.size());
            }
        }
    }

    static class Customer implements Runnable {
        private LinkedBlockingQueue queue;
        private int id;

        Customer(LinkedBlockingQueue queue, int id) {
            this.queue = queue;
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (queue.size() > 0) {
                    queue.remove();
                    System.out.println(" substract " + queue.size());
                } else {
                    System.out.println("东西没了");
                }
            }
        }
    }

    /* 如何让两个线程依次执行？ Thread.join() */
    /* 四个线程 MessageBean B C D，其中 D 要等到 MessageBean B C 全执行完毕后才执行，而且 MessageBean B C 是同步运行的 CountdownLatch */
    public void countdownLatch() {
        int limit = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(limit);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("等待其余线程" + countDownLatch.getCount());
                    countDownLatch.await(5, TimeUnit.SECONDS);
                    //主线程的await操作需要设置超时时间，避免因子线处理异常而长时间一直等待，如果中断需要抛出异常或返回错误结果
                    System.out.println("主线程结束");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        countDownLatch.countDown();
                        System.out.println("其余线程结束" + countDownLatch.getCount());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /* 三个运动员各自准备，等到三个人都准备好后，再一起跑 */
    public void cyclicBarrier() {
        int limit = 3;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(limit);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long time = new Random().nextInt(2000);
                        Thread.sleep(time);
                        System.out.println("我是线程之一，我准备好了，时间" + time);
                        cyclicBarrier.await();
                        System.out.println("跑！" + time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /* Phaser替代CountDownLatch */

    public void phaser1() {
        final int limit = 4;
        final Phaser phaser = new Phaser(limit);
        new Thread(new Runnable() {
            @Override
            public void run() {
                phaser.awaitAdvance(limit);
                //主线程的await操作需要设置超时时间，避免因子线处理异常而长时间一直等待，如果中断需要抛出异常或返回错误结果
                System.out.println("主线程结束");
            }
        }).start();
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        phaser.arrive();
                        System.out.println("其余线程结束" + phaser.getUnarrivedParties());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /* Phaser替代CyclicBarrier */
    public void phaser2() {
        int limit = 3;
        final Phaser phaser = new Phaser(limit);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long time = new Random().nextInt(2000);
                        Thread.sleep(time);
                        System.out.println("我是线程之一，我准备好了，时间" + time);
                        phaser.arriveAndAwaitAdvance();
                        System.out.println("跑！" + time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /* Phaser 动态变更 */
    public void phaser3() {
        int limit = 3;
        final Phaser phaser = new Phaser(limit);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long time = new Random().nextInt(2000);
                        Thread.sleep(time);
                        System.out.println("我是线程之一，我准备好了，时间" + time);
                        phaser.arriveAndAwaitAdvance();
                        System.out.println("跑！" + time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.register(); // 就是+1,个数从3变成了4
        phaser.bulkRegister(1);//再+1，个数从4变成了5
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long time = new Random().nextInt(2000);
                        Thread.sleep(time);
                        System.out.println("我是线程之一，我准备好了，时间" + time);
                        phaser.arriveAndAwaitAdvance();
                        System.out.println("跑！" + time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    public static void main(String[] args) {
        MultiThread multiThread = new MultiThread();
        multiThread.phaser3();
    }
    /* 子线程完成某件任务后，把得到的结果回传给主线程 */
    // 见MyCallableTest
}
