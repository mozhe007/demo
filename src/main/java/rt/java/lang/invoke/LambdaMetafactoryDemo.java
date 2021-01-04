package rt.java.lang.invoke;


import commonbean.Bean;

import java.lang.invoke.*;

/**
 * 这个类只有2个 public static的方法
 */
public class LambdaMetafactoryDemo  {

    @FunctionalInterface
    interface GetterFunction {
        String invoke(Bean callable);
    }

    public void metafactory() throws Throwable {
        GetterFunction getterFunction;
        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class, Bean.class);
        final CallSite site = LambdaMetafactory.metafactory(lookup,
                "invoke",
                MethodType.methodType(GetterFunction.class),
                methodType,
                lookup.findVirtual(Bean.class, "getId", MethodType.methodType(String.class)),
                //new MethodHandleDemo().getMethodHandle(),
                methodType);
        getterFunction = (GetterFunction) site.getTarget().invokeExact();
        System.out.println(getterFunction.invoke(new Bean("Ann")));
    }

    public void altfactory() throws Throwable {
        GetterFunction getterFunction;
        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class, Bean.class);
        final CallSite site = LambdaMetafactory.altMetafactory(lookup,
                "invoke",
                MethodType.methodType(GetterFunction.class),
                methodType,
                lookup.findVirtual(Bean.class, "getId", MethodType.methodType(String.class)),
                //new MethodHandleDemo().getMethodHandle(),
                methodType);
        getterFunction = (GetterFunction) site.getTarget().invokeExact();
        System.out.println(getterFunction.invoke(new Bean("Ann")));
    }



    public static void main(String[] args) {
        LambdaMetafactoryDemo lambdaMetafactoryDemo = new LambdaMetafactoryDemo();
        try {
            lambdaMetafactoryDemo.metafactory();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }




}
