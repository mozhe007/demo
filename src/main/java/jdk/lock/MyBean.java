package jdk.lock;

import java.util.concurrent.locks.ReentrantLock;



public class MyBean {

    // 注意，根据不同的情况判读是否需要static修饰
    private final ReentrantLock lock = new ReentrantLock();
    private int id;
    private int money;

    MyBean(int id, int money) {
        this.id = id;
        this.money = money;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
