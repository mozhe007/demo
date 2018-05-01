package lock;

/*
  synchronized 是JVM 实现锁，ReentrantLock是JDK 实现锁
1 ReenTrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。
2 ReenTrantLock提供了一个Condition（条件）类，用来实现分组唤醒需要唤醒的线程们，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。
3 ReenTrantLock提供了一种能够中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制。
*/
public class MyReentrantLock {

    /*lock -> 调用后一直阻塞到获得锁
    tryLock -> 尝试是否能获得锁 如果不能获得立即返回
    lockInterruptibly -> 调用后一直阻塞到获得锁 但是接受中断信号*/
    public void lock1(MyBean myBean) {
        myBean.getLock().lock();
        try {
            System.out.println("lock1");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //   myBean.getLock().unlock();
        }
    }

    public void lock2(MyBean myBean) {
        myBean.getLock().lock();
        try {
            System.out.println("lock2");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //   myBean.getLock().unlock();
        }
    }

    public void tryLock(MyBean myBean) {
        if (myBean.getLock().tryLock()) {
            System.out.println(" tryLock -- get lock");
        } else {
            System.out.println(" tryLock -- not get lock");
        }
    }

    public void lockInterruptibly(MyBean myBean) {
        try {
            System.out.println(" lockInterruptibly -- start");
            myBean.getLock().lockInterruptibly();
            System.out.println(" lockInterruptibly -- end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void unlock(MyBean myBean) {
        myBean.getLock().unlock();
    }

}
