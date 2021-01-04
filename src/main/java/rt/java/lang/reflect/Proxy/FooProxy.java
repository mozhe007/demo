package rt.java.lang.reflect.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FooProxy implements InvocationHandler {

    // 如果  invoke 方法不使用被代理的对象（这个例子里是delegate ）原来的方法
    private Object delegate;

    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(
                this.delegate.getClass().getClassLoader(), this.delegate
                        .getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("i am proxy 1 ");
        Object object = null;
        if (method != null && delegate!=null) {
            object = method.invoke(this.delegate, args);
        }
        System.out.println("i am proxy 2");
        return object;
    }

    @Override
    public String toString (){
        return "abc";
    }

}
