package rt.java.lang.reflect;

import rt.java.lang.reflect.Proxy.Foo;
import rt.java.lang.reflect.Proxy.FooImpl;
import rt.java.lang.reflect.Proxy.FooProxy;

public class InvocationHandlerDemo {


    /*private class FooProxy implements  InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            System.out.println("i am proxy 1 ");
            Object object = method.invoke(args);
            System.out.println("i am proxy 2");
            return object;
        }
    }

    class FooImp implements Foo {
        public void abc(){
            System.out.println();
        }
    }

    interface Foo{
        void abc();
    }*/


    public static void main(String[] args) {
        FooProxy fooProxy = new FooProxy();
        FooImpl fooImpl = new FooImpl();
        Foo foo =  (Foo) fooProxy.bind(fooImpl);
        foo.abc();
    }
}
