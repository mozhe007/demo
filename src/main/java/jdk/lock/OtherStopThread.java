package jdk.lock;

public class OtherStopThread implements Runnable {

    MyBean myBean;

    OtherStopThread(MyBean myBean) {
        this.myBean = myBean;
    }

    @Override
    public void run() {
        try {
            System.out.println(" run ");
            myBean.getLock().lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println(" Interrupted ！");
            e.printStackTrace();
        }
    }

}
