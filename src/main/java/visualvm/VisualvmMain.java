package visualvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VisualvmMain {

    public static void makeObject() {
        SingleObject singleObject = new SingleObject("single", 1);
        MultiObject multiObject1 = new MultiObject("multi", 1);
        MultiObject multiObject2 = new MultiObject("multi", 2);
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VisualvmMain.makeObject();
    }
}
