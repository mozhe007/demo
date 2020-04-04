package designPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举模式
 */
public class EnumSingleton {

    private EnumSingleton(){}

    /**
     * 采用 内部类 枚举的方式 实例化对象
     */
    private enum EnumSingletonDemo{
        INSTANCE;
        private EnumSingleton instance;

        EnumSingletonDemo(){
            instance = new EnumSingleton();
        }
    }

    public static EnumSingleton getInstance(){
        return EnumSingletonDemo.INSTANCE.instance;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            new Thread(()->{
                System.out.println(EnumSingleton.getInstance());
            }).start();
        }

        try {
            Class clas = Class.forName("designPattern.EnumSingleton");
            Constructor a = clas.getConstructor();
            a.newInstance("1");
            clas.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
