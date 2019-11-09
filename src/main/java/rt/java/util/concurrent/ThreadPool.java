package rt.java.util.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    static class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("线程耗时");
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyThread());
        }
    }

    public void newFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyThread());
        }
    }

    public void newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyThread());
        }
    }

    public void newScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        System.out.println("5秒后触发");
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行任务");
            }
        }, 5, TimeUnit.SECONDS);
        // 重复执行
        System.out.println("重复执行触发");
        executorService.scheduleAtFixedRate(new MyThread(),1,1,TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.newScheduledThreadPool();
    }

}
