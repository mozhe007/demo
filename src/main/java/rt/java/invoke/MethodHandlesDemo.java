package rt.java.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodHandles.publicLookup;
import static java.lang.invoke.MethodType.methodType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodHandlesDemo {

    //官方例子
    public void lookupDemo() throws Throwable {
        MethodHandle MH_concat = publicLookup().findVirtual(String.class,
                "concat", methodType(String.class, String.class));
        MethodHandle MH_hashCode = publicLookup().findVirtual(Object.class,
                "hashCode", methodType(int.class));
        MethodHandle MH_hashCode_String = publicLookup().findVirtual(String.class,
                "hashCode", methodType(int.class));
        assertEquals("xy", (String) MH_concat.invokeExact("x", "y"));
        assertEquals("xy".hashCode(), (int) MH_hashCode.invokeExact((Object) "xy"));
        assertEquals("xy".hashCode(), (int) MH_hashCode_String.invokeExact("xy"));
        // interface method:
        MethodHandle MH_subSequence = publicLookup().findVirtual(CharSequence.class,
                "subSequence", methodType(CharSequence.class, int.class, int.class));
        assertEquals("def", MH_subSequence.invoke("abcdefghi", 3, 6).toString());

        // constructor "internal method" must be accessed differently:
        MethodType MT_newString = methodType(void.class); //()V for new String()
        try {
            assertEquals("impossible", lookup()
                    .findVirtual(String.class, "<init>", MT_newString));
        } catch (NoSuchMethodException ex) {
        } // OK
        MethodHandle MH_newString = publicLookup()
                .findConstructor(String.class, MT_newString);
        assertEquals("", (String) MH_newString.invokeExact());
    }

    // look 和 publicLookup 区别
    public void lookup1(){
        // 所有方法
        MethodHandles.Lookup look = MethodHandles.lookup();
        // public 方法
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
    }

}
