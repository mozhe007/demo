package rt.java.invoke;

import java.lang.invoke.MethodType;
import java.util.Map;

public class MethodTypeDemo {
    public void methodType() {
        // 如果方法是 Map foo(String key , String value)
        MethodType.methodType(Map.class, String.class, String.class);
    }

    public static void main(String[] args) {

    }
}
