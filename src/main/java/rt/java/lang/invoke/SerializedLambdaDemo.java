package rt.java.lang.invoke;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.IntFunction;

public class SerializedLambdaDemo implements Serializable {

    public void getImplClass () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IntFunction hex = (IntFunction<String> &Serializable)Integer::toHexString;
        // writeReplace 是 lamda 底层的方法
        Method m = hex.getClass().getDeclaredMethod("writeReplace");
        m.setAccessible(true);
        SerializedLambda sl = (SerializedLambda) m.invoke(hex);
        System.out.println("cap: " + sl.getCapturingClass());
        System.out.println("target: " + sl.getImplClass() + " " + sl.getImplMethodName());
        //cap: rt/java/invoke/SerializedLambdaDemo
        //target: java/lang/Integer toHexString
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        SerializedLambdaDemo serializedLambdaDemo = new SerializedLambdaDemo();
        serializedLambdaDemo.getImplClass();
    }

}
