package rt.java.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/*
@Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
（必须和@Retention(RetentionPolicy.RUNTIME)）

结论：
类继承关系中@Inherited的作用
类继承关系中，子类会继承父类使用的注解中被@Inherited修饰的注解
接口继承关系中@Inherited的作用
接口继承关系中，子接口不会继承父接口中的任何注解，不管父接口中使用的注解有没有被@Inherited修饰
类实现接口关系中@Inherited的作用
类实现接口时不会继承任何接口中定义的注解
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedDemo {

}
@Retention(RetentionPolicy.RUNTIME)
@interface NoInherited{
}
@InheritedDemo
class InheritedFather{
}
@NoInherited
class NoInheritedFather{
}

class InheritedSun extends InheritedFather{
    public static void main(String[] args) {
        System.out.println(InheritedSun.class.getAnnotations()); //  InheritedDemo
        System.out.println(NoInheritedSun.class.getAnnotations()); // empty
    }
}
class NoInheritedSun extends NoInheritedFather{

}

