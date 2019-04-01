package rt.java.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy {
    public static void main(String[] args) {
        Singer singer = new Singer();
        Object newProxyInstance = java.lang.reflect.Proxy.newProxyInstance(
                singer.getClass().getClassLoader(),
                singer.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("22222");
                            method.invoke(singer, args);
                            System.out.println("3333");
                            return null;
                    }
                });
        ISinger i = (ISinger) newProxyInstance;
        i.sing();
        ((ISinger) newProxyInstance).sing();
    }
}
class Singer implements ISinger {
    public void sing() {
        System.out.println("sing");
    }
}
interface ISinger {
    public void sing();
}
