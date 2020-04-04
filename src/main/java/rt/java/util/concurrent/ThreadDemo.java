package rt.java.util.concurrent;

public class ThreadDemo {

    public void t (){
        Thread thread1 = new Thread(()->{
            System.out.println(1);
        });
        Thread thread2 = new Thread(()->{
            System.out.println("2");
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(()->{
            System.out.println("3");
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();
        thread2.start();
        thread1.start();
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.t();

        Thread thread = new Thread();
        thread.run();
    }
}
