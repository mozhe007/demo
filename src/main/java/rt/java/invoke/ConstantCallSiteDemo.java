package rt.java.invoke;


import commonbean.FooService;

import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/*
* ConstantCallSite
* */
public class ConstantCallSiteDemo {

    public void dynamicInvoker() throws NoSuchMethodException, IllegalAccessException {
        /*
         不能直接这样写,因为
        java.lang.IllegalAccessException: no private access for invokespecial: class commonbean.FooService, from rt.java.invoke.ConstantCallSiteDemo

        Method m=Person.class.getDeclaredMethod("privateInfo");
        m.setAccessible(true); //破坏掉
        */

        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        MethodHandle methodHandle1 = lookup.findSpecial(FooService.class, "foo", MethodType.methodType(void.class), FooService.class);
        /*MethodHandleDemo methodHandleDemo = new MethodHandleDemo();
        MethodHandle methodHandle1 = methodHandleDemo.getMethodHandle();*/
        ConstantCallSite constantCallSite = new ConstantCallSite(methodHandle1);
        MethodHandle methodHandle2 = constantCallSite.dynamicInvoker();
        assert  methodHandle1==methodHandle2;
    }


    public static void main(String[] args) {
        ConstantCallSiteDemo constantCallSiteDemo = new ConstantCallSiteDemo();
        try {
            constantCallSiteDemo.dynamicInvoker();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
