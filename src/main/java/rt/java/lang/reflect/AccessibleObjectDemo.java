package rt.java.lang.reflect;

import commonbean.PrivateBean;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AccessibleObjectDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class class1 = PrivateBean.class;
        Method method = class1.getDeclaredMethod("privateMethod");
        PrivateBean privateBean = (PrivateBean) class1.newInstance();
        // 如果没有下面这句，报 java.lang.IllegalAccessException
        AccessibleObject.setAccessible(new Method[]{method},true);
        method.invoke(privateBean,null);
    }
}
