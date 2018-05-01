package lock;

public class OtherObject {

    public void lock1(MyBean myBean) {
        myBean.getLock().lock();
        try {
            System.out.println("OtherObject . lock1");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //   myBean.getLock().unlock();
        }
    }
}
