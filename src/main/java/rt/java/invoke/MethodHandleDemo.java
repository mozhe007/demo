package rt.java.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;


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

    public static void main(String[] args) {
        MethodHandleDemo methodHandleDemo = new MethodHandleDemo();
        methodHandleDemo.test();
    }
}
