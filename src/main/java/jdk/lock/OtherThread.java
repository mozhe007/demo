package jdk.lock;

import java.util.concurrent.TimeUnit;

public class OtherThread implements Runnable {

    MyBean myBean;

    OtherThread(MyBean myBean) {
        this.myBean = myBean;
    }

    @Override
    public void run() {
        myBean.getLock().lock();
        System.out.println(" run ! ");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myBean.getLock().unlock();
    }

}
