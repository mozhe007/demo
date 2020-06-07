package rt.java.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierDemo {

    // pub 为例，还有其余3个
    public void pub(){
        try {
            Method method = ModifierDemo.class.getMethod("pub");
            System.out.println(Modifier.isPublic(method.getModifiers()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
