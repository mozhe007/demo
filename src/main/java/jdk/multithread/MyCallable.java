package jdk.multithread;

import java.util.Random;
import java.util.concurrent.*;

public class MyCallable implements Callable {

    @Override
    public Object call() {
        return null;
    }

    public void callable() {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                Thread.sleep(5000);// 可能做一些事情
                System.out.println("可能做一些事情");
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            System.out.println("在future.get()阻塞");
            System.out.println(future.get());
            System.out.println("在future.get()结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void callableExecutorService() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() {
                return new Random().nextInt(100);
            }
        });
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
