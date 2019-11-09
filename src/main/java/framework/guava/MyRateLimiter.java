package framework.guava;

import com.google.common.util.concurrent.RateLimiter;

public class MyRateLimiter {
    RateLimiter rateLimiter = RateLimiter.create(500); // 速率是2令牌/秒


    void tryAcquire() {
        if (rateLimiter.tryAcquire()) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }

    static void tryAcquireAllTest(){
        MyRateLimiter myRateLimiter = new MyRateLimiter();
        for(int i=0;i<10;i++){
            myRateLimiter.tryAcquire();
        }
    }

    void tryAcquireSmoothTest() {
        MyRateLimiter myRateLimiter = new MyRateLimiter();
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myRateLimiter.tryAcquire();
        }
    }

    /**
     *
     */
    void acquireTest(){
        System.out.println(rateLimiter.acquire(1));  //不会等待
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
        //因为闲置了一段时间，桶中已经存在令牌，能够应对突发流量
        System.out.println(rateLimiter.acquire(1));  //不会等待
        System.out.println(rateLimiter.acquire(1));  //不会等待
        System.out.println(rateLimiter.acquire(1));  //不会等待
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
    }

    public static void main(String[] args) {
        MyRateLimiter myRateLimiter = new MyRateLimiter();
        //

        myRateLimiter.acquireTest();

    }
}
