package jdk.multithread;


public class DeadLockTest {

    static class TestThread implements Runnable {
        private DeadLock deadLock;
        private int i ;

        TestThread(DeadLock deadLock, int i) {
            this.deadLock = deadLock;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (i == 1) {
                    deadLock.getObject1();
                }
                if (i == 2) {
                    deadLock.getObject2();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        TestThread testThread1 = new TestThread(deadLock,1);
        TestThread testThread2 = new TestThread(deadLock,2);
        new Thread(testThread1).start();
        new Thread(testThread2).start();
    }
}
