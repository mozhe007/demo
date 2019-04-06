package rt.java.util.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {

    //LinkedBlocking 不能无限添加， 超过capacity会报异常java.lang.IllegalStateException: Queue full
    void  capacityTest(){
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(5);
        linkedBlockingQueue.add(1);
        linkedBlockingQueue.add(2);
        linkedBlockingQueue.add(3);
        linkedBlockingQueue.add(4);
        linkedBlockingQueue.add(5);
        System.out.println("5个了");
        linkedBlockingQueue.add(6);
    }

    public static void main(String[] args) {
        LinkedBlockingQueueDemo linkedBlockingQueueDemo = new LinkedBlockingQueueDemo();
        linkedBlockingQueueDemo.capacityTest();
    }
}
