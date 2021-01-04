package rt.java.lang.reflect;

import java.lang.reflect.Method;

public interface MyOwnHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
