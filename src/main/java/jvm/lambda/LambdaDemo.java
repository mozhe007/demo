package jvm.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * 静态方法引用（static method）语法：classname::methodname 例如：Person::getAge
     * 对象的实例方法引用语法：instancename::methodname 例如：System.out::println
     * 对象的超类方法引用语法： super::methodname
     * 类构造器引用语法： classname::new 例如：ArrayList::new
     * 数组构造器引用语法： typename[]::new 例如： String[]:new
     */
    public void doublequote() {
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
       // collected = collected.stream().map(String::toUpperCase).collect(collectors.toCollection(ArrayList::new));
    }

    @FunctionalInterface
    interface foo{
        String invoke(HashMap ha);
    }

    public static void main(String[] args) {
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
    }
}
