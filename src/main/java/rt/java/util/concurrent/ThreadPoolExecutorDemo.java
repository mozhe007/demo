package rt.java.util.concurrent;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
workerCount 类似于存活的线程。
1.当workerCount小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
2.当workerCount达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行
3.当workQueue已满，且workerCount < maximumPoolSize时，新提交任务会创建新线程执行任务
4.当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理
5.当workerCount中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程
6.当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
*/

public class ThreadPoolExecutorDemo {

    /**
     * 演示<li> If fewer than corePoolSize threads are running, the Executor
     * always prefers adding a new thread
     * rather than queuing.</li>
     * <p>
     * <li> If corePoolSize or more threads are running, the Executor
     * always prefers queuing a request rather than adding a new
     * thread.</li>
     */
    public void order() {
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 10;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue);
        for (int i = 1; i <= 20; i++) {
            System.out.println("提交第" + i + "个任务!");
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(atomicInteger.addAndGet(1));
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 截取 ThreadPoolExecutor.execute(Runnable command)方法的代码。
     */
    public void executeTest() {
        /*
        int c = ctl.get();
        // 1. If fewer than corePoolSize threads are running, try to
        // start a new thread with the given command as its first
        // task.
        // 1. 当workerCount小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
        if (workerCountOf(c) < corePoolSize) {
            // add
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        //  2. If a task can be successfully queued, then we still need
        //         * to double-check whether we should have added a thread
        //         * (because existing ones died since last checking) or that
        //         * the pool shut down since entry into this method. So we
        //         * recheck state and if necessary roll back the enqueuing if
        //         * stopped, or start a new thread if there are none.
        //  2. 当workerCount达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        // 3. If we cannot queue task, then we try to add a new
        //         * thread.  If it fails, we know we are shut down or saturated
        //         * and so reject the task.
        // 3.当workQueue已满，且workerCount < maximumPoolSize时，新提交任务会创建新线程执行任务
        else if (!addWorker(command, false))
        // 当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理
            reject(command);
        */
    }

    /**
     *   参数： firstTask：the task the new thread should run first
     *     应该第一个运行的任务，null就从workQueue里面取
     *   参数： core：if true use corePoolSize as bound, else
     *           maximumPoolSize
     *     用来当作边界的值，corePoolSize/maximumPoolSize
     * 原理：
     *   阶段1，先利用自旋锁，维护AtomicInteger ctl ，使+1
     *   阶段2  创建并运行新线程
     * 代码：
     * 1. 检查状态。如果关闭了，就返回false.
     * 2. 判断核心数量，如果大于目标值(corePoolSize : maximumPoolSize)就返回false
     * 3. 比较当前的状态，如果运行状态改变了，从 retry 段重新开始
     */
    boolean addWorkerTest(Runnable firstTask, boolean core) {
        return true;
        /*retry:
        for (; ; ) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // Check if queue empty only if necessary.
            // 1. 检查状态。如果关闭了，就返回false.
            if (rs >= SHUTDOWN &&
                    !(rs == SHUTDOWN &&
                            firstTask == null &&
                            !workQueue.isEmpty()))
                return false;

            // 2. 判断核心数量，如果大于目标值(corePoolSize : maximumPoolSize)就返回false
            for (; ; ) {
                int wc = workerCountOf(c);
                if (wc >= CAPACITY ||
                        wc >= (core ? corePoolSize : maximumPoolSize))
                    return false;
                if (compareAndIncrementWorkerCount(c))
                    break retry;
                c = ctl.get();  // Re-read ctl
                // 3.比较当前的状态，如果运行状态改变了，从 retry 段重新开始
                if (runStateOf(c) != rs)
                    continue retry;
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        boolean workerStarted = false;
        boolean workerAdded = false;
        ThreadPoolExecutor.Worker w = null;
        try {
            w = new ThreadPoolExecutor.Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());

                        检查状态。如果关闭了，就返回false.
                    if (rs < SHUTDOWN ||
                            (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) // precheck that t is startable
                            throw new IllegalThreadStateException();
                        workers.add(w);
                        int s = workers.size();
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (!workerStarted)
                addWorkerFailed(w);
        }
        return workerStarted;*/
    };

    public static void main(String[] args) {
        ThreadPoolExecutorDemo threadPoolExecutorDemo = new ThreadPoolExecutorDemo();
        threadPoolExecutorDemo.order();
    }
}
