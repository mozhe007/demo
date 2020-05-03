package rt.java.invoke;

import commonbean.FooService;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodHandles.publicLookup;
import static java.lang.invoke.MethodType.methodType;
import static org.junit.jupiter.api.Assertions.assertEquals;


/*
以下区别：
        1.本质上讲，Relection和MethodHandle机制都是在模拟方法调用，但Reflection是在模拟Java代码层次的方法调用，而MethodHandle是在模拟字节码层次的方法调用。在MethodHandles.lookup中的3个方法-findStaitc、findVirtual、findSpecial正是为了对应于invokestatic、invokevirtual和invokespecial这几条字节码指令的执行权限校验行为，而这些底层细节在使用Reflection API时是不需要关心的。
        2.Reflection中的java.lang.reflect.Method对象远比MethodHandle机制信息含量多，换句话说，Reflection是重量级，而MethodHandle是轻量级。
        3.由于MethodHandle是对字节码的方法指令调用的模拟，所以理论上虚拟机做了各种优化。
        4.Reflection API的设计目标是只为Java语言服务的，而MethodHandle则设计成可服务于所有Java虚拟机之上的语言，其中也包括Java。
 */
public class MethodHandleDemo {

    public void foo() {
        System.out.println("hello world");
    }

    public MethodHandle getMethodHandle(){
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = null;
        try {
            methodHandle = lookup.findSpecial(MethodHandleDemo.class, "foo", MethodType.methodType(void.class), MethodHandleDemo.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return methodHandle;
    }


    public void test() {
        try {
            //1.Reflection
            Class clazz = MethodHandleDemo.class;
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("foo");
            method.invoke(obj);
            //2. MethodHandle
            MethodHandleDemo mht = new MethodHandleDemo();
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle methodHandle = lookup.findSpecial(MethodHandleDemo.class, "foo", MethodType.methodType(void.class), MethodHandleDemo.class);
            // MethodHandle methodHandle = lookup.findVirtual(MethodHandleTest.class, "printMessage", MethodType.methodType(void.class));
            methodHandle.invokeExact(mht);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /*  注意 : 当编译器发出invokeExact调用时,它会将Object记录为预期的返回类型.从MethodHandle javadoc(强调我的)：
    *   在运行时,方法句柄实际上返回RegexPathMatcher,所以invokeExact失败了WrongMethodTypeException.
        您需要使用(编译时)转换显式指定返回类型：
    * */
    public void invoke() throws Throwable {
        MethodHandle methodHandle1 = publicLookup().findVirtual(FooService.class, "foo", MethodType.methodType(String.class, String.class ));
        //
        // methodHandle1.invokeExact(new FooService(), "123"); 如果没有 (String)强转报错.
        String b = (String) methodHandle1.invoke(new FooService(), "123");
        System.out.println(b);
    }

    public void invokeExact() throws Throwable {
        MethodHandle methodHandle1 = publicLookup().findVirtual(FooService.class, "foo", MethodType.methodType(String.class, String.class ));
        // methodHandle1.invokeExact(new FooService(), "123"); 如果没有强转报错.
        String b = (String) methodHandle1.invokeExact(new FooService(), "123");
        System.out.println(b);
    }

    /**
     *  invoke 和 invokeExact很像,区别是 invoke 可以做类型转换, invokeExact 不可以.
     *  这个例子,原方法的参数是int ,我用Integer做测试
     *
     * @throws Throwable
     */
    public void differenceWithinvokeAndinvokeExact() throws Throwable {
        MethodHandle methodHandle1 = publicLookup().findVirtual(FooService.class, "foo", MethodType.methodType(String.class, int.class ));

        // invoke 类型转换是OK的
        String ok = (String) methodHandle1.invoke(new FooService(), new Integer(123));
        // invokeExact 类型转换会报 java.lang.invoke.WrongMethodTypeException
        String notOk = (String) methodHandle1.invokeExact(new FooService(), new Integer(123));
    }

    public static void main(String[] args) {
        MethodHandleDemo methodHandleDemo = new MethodHandleDemo();
        try {
            methodHandleDemo.differenceWithinvokeAndinvokeExact();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
