package generic;

import sun.net.www.content.text.Generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Generics<T> {

    private T t;

    public Generics(T t) {
        this.t = t;
    }

    public void genericsVoid() {

    }

    // 返回类的泛型
    public T genericsT() {
        return t;
    }

    // 返回方法的泛型1
    public <T> T method1(T t) {
        return t;
    }

    // 返回方法的泛型2
    public <K, V> void method2(K k, V v) {
        System.out.println(k.getClass());
        System.out.println(v.getClass());
    }

    // 泛型的Class
    public <C> void method3(Class<C> c) throws IllegalAccessException, InstantiationException {
        c.newInstance();
    }

    // 不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    // 此处’？’是类型实参，而不是类型形参
    public void method4(Generics<?> g) {

    }

    // 泛型 extends
    public <T extends Number> void extends1(T t) {
        t.byteValue(); // Number类型的方法；
    }

    // 泛型 extends ,有返回值
    public <T extends Number> T extends2(T t) {
        t.byteValue(); // Number类型的方法；
        return t;
    }

    // 泛型 extends 继承各自不一样的，用 &区分
    public <T, S extends Number & Comparable> void extends3(T t, S s) {

    }

    // 泛型 super
    public void super1() {
        List<? super T> list = new ArrayList<T>();
    }

}
