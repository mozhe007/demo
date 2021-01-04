package rt.java.lang.invoke;

import java.lang.invoke.*;

public class MutableCallSiteDemo {

    public void dynamicInvoker() {
        /*
        不能直接这样写,因为
        no private access for invokespecial:
        class rt.java.lang.invoke.ConstantCallSiteDemo, from rt.java.lang.invoke.MutableCallSiteDemo */
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle1 = null;
        try {
            methodHandle1 = lookup.findVirtual(ConstantCallSiteDemo.class, "foo", MethodType.methodType(void.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
       /* MethodHandleDemo methodHandleDemo = new MethodHandleDemo();
        MethodHandle methodHandle1 = methodHandleDemo.getMethodHandle();
*/
        MutableCallSite constantCallSite = new MutableCallSite(methodHandle1);
        MethodHandle methodHandle2 = constantCallSite.dynamicInvoker();
        assert  methodHandle1==methodHandle2;
    }
    //官方提供的例子
    public void a()  {
        MutableCallSite name = new MutableCallSite(MethodType.methodType(String.class));
        MethodHandle methodHandle = name.dynamicInvoker();
        MethodType methodType = MethodType.methodType(String.class);

        MethodHandle strMethodHandle = null;
        try {
            // 定义 String类的 toUpperCase 方法
            strMethodHandle = MethodHandles.lookup().findVirtual(String.class, "toUpperCase", methodType);
            MethodHandle worker1 = MethodHandles.filterReturnValue(methodHandle, strMethodHandle);
            name.setTarget(MethodHandles.constant(String.class, "Rocky"));
            System.out.println((String)worker1.invokeExact());

            assert "ROCKY".equalsIgnoreCase( (String)worker1.invokeExact() );
            name.setTarget(MethodHandles.constant(String.class, "Fred"));
            System.out.println((String)worker1.invokeExact());
            assert "FRED".equalsIgnoreCase( (String) worker1.invokeExact());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    public static void main(String[] args)  {
        MutableCallSiteDemo mutableCallSiteDemo = new MutableCallSiteDemo();
        mutableCallSiteDemo.dynamicInvoker();
    }
}
