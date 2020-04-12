package rt.java.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
//此注解类型的信息只会记录在源文件中，编译时将被编译器丢弃，也就是说
//不会保存在编译好的类信息中
    SOURCE,
//编译器将注解记录在类文件中，但不会加载到JVM中。如果一个注解声明没指定范围，则系统
//默认值就是Class
    CLASS,
//注解信息会保留在源文件、类文件中，在执行的时也加载到Java的JVM中，因此可以反射性的读取。
    RUNTIME*/
public class RetentionDemo {
    public static void main(String[] args) {
        RetentionSourceClass a = new RetentionSourceClass();   // 反编译class文件,里面没有@RetentionSource ,性能好
        RetentionClassClass b = new RetentionClassClass();     // 反编译class文件,里面有@RetentionClass ,方便查看编译文件
        RetentionRuntimeClass c = new RetentionRuntimeClass(); // 反编译class文件,里面有@RetentionRuntime ,只有这个可以反射

        Class<RetentionClassClass> clas1  = RetentionClassClass.class;
        Class<RetentionRuntimeClass> clas2  = RetentionRuntimeClass.class;
        System.out.println(clas1.getAnnotations().length); // 0
        System.out.println(clas2.getAnnotations().length); // 1
        // 或者..
        @RetentionClass
        class C {}
        assert C.class.getAnnotations().length == 0;

        @RetentionRuntime
        class D {}
        assert D.class.getAnnotations().length == 1;
    }
}
@Retention(RetentionPolicy.SOURCE)
@interface RetentionSource {
}
@Retention(RetentionPolicy.CLASS)
@interface RetentionClass {
}
@Retention(RetentionPolicy.RUNTIME)
@interface RetentionRuntime {
}

@RetentionSource
class RetentionSourceClass{
}

@RetentionClass
class RetentionClassClass{
}
@RetentionRuntime
class RetentionRuntimeClass{
}