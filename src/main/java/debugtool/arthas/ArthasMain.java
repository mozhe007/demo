package debugtool.arthas;

import java.util.UUID;

public class ArthasMain {
    /**
     * @return
     */
    public static String uuid(int i) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            System.out.println("uuid = " + uuid(i++));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
