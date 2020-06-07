package rt.java.reflect;

import rt.java.reflect.Proxy.Foo;
import rt.java.reflect.Proxy.FooImpl;
import rt.java.reflect.Proxy.FooProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {

    // 一个最简单的例子
    public void demo() {
        // 必须 new一个 target让  invoke() 方法调用
        FooImpl target = new FooImpl();

        Object newProxyInstance  = Proxy.newProxyInstance(
                // 1.目标的类加载器
                target.getClass().getClassLoader(),
                // 2.目标的实现的接口
                target.getClass().getInterfaces(),
                // 3. 自定义的处理逻辑
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(2);
                        method.invoke(target);
                        System.out.println(2);
                        return null;
                    }
                });
        Foo i = (Foo) newProxyInstance;
        i.abc(); // 写法1
        ((Foo) newProxyInstance).abc(); // 写法2

    }


    //获取 InvocationHandler , 但个人感觉这个方法没屁用
    public void getInvocationHandler() {
        FooProxy fooProxy = new FooProxy();
        FooImpl fooImpl = new FooImpl();
        Foo foo = (Foo) fooProxy.bind(fooImpl);
        // invocationHandler == fooImpl
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(foo);
        try {
            invocationHandler.invoke(null, null, null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    //获取 ProxyClass, 但个人感觉这个方法没屁用
    public Class getProxyClass() {
        FooProxy fooProxy = new FooProxy();
        FooImpl fooImpl = new FooImpl();
        Foo foo = (Foo) fooProxy.bind(fooImpl);
        // invocationHandler == fooImpl'

        //第一个接口是获取classloader, 传接口 或实现类的classloader都一样；  第2个参数必须是接口
        Class cl = Proxy.getProxyClass(foo.getClass().getClassLoader(), Foo.class);
        System.out.println(cl.getName()); // com.sun.proxy.$Proxy0
        return cl;
    }


    public void isProxyClass() {
        FooProxy fooProxy = new FooProxy();
        FooImpl fooImpl = new FooImpl();
        Foo foo = (Foo) fooProxy.bind(fooImpl);

        boolean a = Proxy.isProxyClass(getProxyClass());
        System.out.println(a);
    }

    public void newProxyInstance() {
        // 代理类
        FooProxy fooProxy = new FooProxy();
        //
        FooImpl fooImpl = new FooImpl();
        // 如果有这句  ，那么object == foo,
        // 如果没有这句，那么object == fooProxy
        // 接口
        Foo foo = (Foo) fooProxy.bind(fooImpl);
        // 等价上面
        //Foo object =  (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(), FooImpl.class.getInterfaces(), fooProxy);
        foo.abc();
    }

    public static void main(String[] args) {
        ProxyDemo proxyDemo = new ProxyDemo();
        proxyDemo.demo();
    }


}
