package jdk.multithread;

public class DeadLock {
    private Object object1 =new Object();
    private Object object2 =new Object();
    public Object getObject1() throws InterruptedException {
        synchronized (object1){
            System.out.println("getObject1（）获取 object1锁");
            Thread.sleep(2000);
            synchronized (object2){
                System.out.println("getObject1（）获取 object2锁");
            }
        }
        return object1;
    }

    public Object getObject2() throws InterruptedException {
        synchronized (object2){
            System.out.println("getObject2（）获取 object2锁");
            Thread.sleep(2000);
            synchronized (object1){
                System.out.println("getObject2（）获取 object1锁");
            }
        }
        return object2;
    }
}
