package rt.java.lang.reflect;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Calendar;

public class InvocationHandlerDemo implements InvocationHandler {


    private int handlerId;  //业务属性，比如周几

    public int getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(int handlerId) {
        this.handlerId = handlerId;
    }

    private Object delegate;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("i am proxy " + handlerId);
        Object object = method.invoke(delegate, args);
        System.out.println("i am proxy " + handlerId);
        return object;
    }

    // 核心逻辑是  Proxy.newProxyInstance ，这里写成了一个方法
    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(
                this.delegate.getClass().getClassLoader(), this.delegate
                        .getClass().getInterfaces(), this);
    }

    static class FooImp implements Foo {
        public void hello(String name) {
            System.out.println(name);
        }
    }

    interface Foo {
        void hello(String name);
    }


    public static void main(String[] args) {
        //
        InvocationHandlerDemo fooProxy = new InvocationHandlerDemo();
        FooImp fooImpl = new FooImp();
        Foo foo = (Foo) fooProxy.bind(fooImpl);
        foo.hello("林建");
        // 上面的感觉是脱了裤子放屁, 并没有看到“动态”的意义，给他加点料

        // 加料部分
        InvocationHandlerDemo fooProxy1 = new InvocationHandlerDemo();
        fooProxy1.setHandlerId(1);
        InvocationHandlerDemo fooProxy2 = new InvocationHandlerDemo();
        fooProxy2.setHandlerId(2);
        // 脑补的， 这里才是动态的
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1 == 1) {
            FooImp newFooImpl = new FooImp();
            Foo newFoo = (Foo) fooProxy1.bind(newFooImpl);
            newFoo.hello("林建");
        } else {
            FooImp newFooImpl = new FooImp();
            Foo newFoo = (Foo) fooProxy2.bind(newFooImpl);
            newFoo.hello("林建");
        }

    }
}
