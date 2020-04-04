import java.io.IOException;

public class Test implements Cloneable{

    public static void main(String[] args) throws IOException {

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
