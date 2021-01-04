package jvm.lambda;

import commonbean.Bean;
import org.aspectj.weaver.ast.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class LambdaDemo {

    public void one(OneFunctionInterface oneFunctionInterface) {
        oneFunctionInterface.function1();
    }

    public void more(MoreFunctionInterface moreFunctionInterface) {

    }

    public void param(ParamInterface paramInterface) {

    }

    public void returnF(ReturnInterface returnInterface){

        System.out.println(returnInterface.function1("112323"));
    }

    /**
     *
     * 官网： https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
     *
     *
     Kind	Example
     Reference to a static method	ContainingClass::staticMethodName
     Reference to an instance method of a particular object	containingObject::instanceMethodName
     Reference to an instance method of an arbitrary object of a particular type	ContainingType::methodName
     Reference to a constructor	ClassName::new

     * 方法引用
     *
     * 静态方法引用（static method）语法：classname::methodname 例如：Person::getAge
     * 对象的实例方法引用语法：instancename::methodname 例如：System.out::println
     * 对象的超类方法引用语法： super::methodname
     * 类构造器引用语法： classname::new 例如：ArrayList::new
     * 数组构造器引用语法： typename[]::new 例如： String[]:new
     *
     */
    public void doublequote() {
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        collected = collected.stream().map(String::toUpperCase).collect(Collectors.toCollection(ArrayList::new));
    }

    @FunctionalInterface
    interface foo{
        String invoke(HashMap ha);
    }

    public void foo() {
        LambdaDemo lambdaDemo = new LambdaDemo();
        //----------------one function--------------
        //jdk 1.7
        lambdaDemo.one(new OneFunctionInterface() {
            @Override
            public void function1() {
                System.out.println("one");
            }
        });
        //jvm.lambda
        lambdaDemo.one(() -> System.out.println("one"));
        //----------------more function--------------
        // not work
        // lambdaDemo.more(()-> System.out.println("no fucntion"));
        // lambdaDemo.more(string->{} System.out.println("no fucntion"));

        //----------------with param function--------------
        //jdk 1.7
        lambdaDemo.param(new ParamInterface() {
            @Override
            public void function1(String string) {
                System.out.println(string);
            }
        });
        // jvm.lambda one line
        lambdaDemo.param(param ->
                System.out.println(param)
        );
        // jvm.lambda more lines
        lambdaDemo.param(param -> {
            System.out.println(param);
            System.out.println(param);
        });

        //----------------return  param function--------------

        // jvm.lambda more lines
        lambdaDemo.returnF(param -> {

            return Integer.parseInt(param);
        });

        // :: 双引号
        String[] array = {"aaaa", "bbbb", "cccc"};
        List<String> list = Arrays.asList(array);
        //Java 7
        for(String s:list){
            System.out.println(s);
        }
        //Java 8
        list.forEach(System.out::println);
    }

}
