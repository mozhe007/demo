package jvm.generic;

import java.util.ArrayList;
import java.util.List;

public class Generics<T> {

    private T t;

    public Generics(T t) {
        this.t = t;
    }

    // 返回  成员变量 泛型
    public T genericsT() {
        return t;
    }

    // 返回 方法参数 泛型
    public <T> T method1(T t) {
        return t;
    }

    // 返回 声明的返回类型 泛型,从jFinal中看到的写法。
    // example:String a = return1(XX);
    // example:Integer a = return1(YY);
    public <T> T return1() {
        return (T) new Object();
    }

    // 返回 方法参数 泛型 ，参数是Class
    public <T> T method2(Class<T> c) {
        Object object = new Object();
        // 或 (c)object;
        return c.cast(object);
    }

    // 多个 方法参数 泛型2
    public <K, V> void method3(K k, V v) {
        System.out.println(k.getClass());
        System.out.println(v.getClass());
    }

    // 方法参数 泛型 ，参数是Class ,实例化
    public <C> void method3(Class<C> c) throws IllegalAccessException, InstantiationException {
        c.newInstance();
    }

    // 不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    // 此处’？’是类型实参，而不是类型形参
    public void method4(Generics<?> g) {

    }


    /*extends 可用于的返回类型限定 extends1 ，也能用于参数类型限定。
      super 只用于参数类型限定，不能用于返回类型限定。
      */
    // 泛型上界 extends
    public <T extends GrandFatherBean> void extends1(T t) {
        t.foo(); // Number类型的方法；
        List<? extends FatherBean> l1 = new ArrayList<>();
    }

    // 泛型上界 extends ,有返回值
    public <T extends GrandFatherBean> T extends2(T t) {
        t.foo(); // Number类型的方法；
        return t;
    }

    // 泛型上界 extends 继承各自不一样的，用 &区分
    public <T, S extends GrandFatherBean & Comparable> void extends3(T t, S s) {

    }

    // 泛型下界super,super只能修饰参数类型
    // 因为对返回类型做super没有任何意义， 编译器无法确定 t的类型，我们只能使用Object类型的方法，但这是本来就知道。
    public <T> void super1(Generics<? super T> t) {
        List<? super FatherBean> l1 = new ArrayList<>();
    }

    //问题 ？与 T 的区别？
    //实际上 ？与 T 不是同一个可比较的关系， T 是指一个确定类型的，但是编译时不知道
    //    ? 仅代表类型 List<?> 没有任何意义
    public void wildcard() {
    }

}
