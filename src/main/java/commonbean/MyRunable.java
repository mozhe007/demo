package commonbean;

public class MyRunable implements Runnable {

    int sequence;

    public MyRunable(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        try {
            System.out.println(sequence);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
