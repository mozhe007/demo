package rt.java.lang;


import commonbean.Bean;
import commonbean.NoConstructorBean;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class ClassDemo {

    public String toString() {

        int intArr[] = {2, 5, -2};
        System.out.println("int 数据" + intArr.getClass().toString());// int 数据class [I

        Integer iIntArr[] = new Integer[]{};
        System.out.println("Integer 数据" + iIntArr.getClass().toString()); //Integer 数据class [Ljava.lang.Integer;

        String str = ClassDemo.class.toString();
        System.out.println(str); //class rt.java.lang.ClassDemo
        return str;
    }

    public String toGenericString() {

        int intArr[] = {2, 5, -2};
        System.out.println("int 数据" + intArr.getClass().toGenericString());//public abstract final class [I

        Integer iIntArr[] = new Integer[]{};
        System.out.println("Integer 数据" + iIntArr.getClass().toGenericString());//public abstract final class [Ljava.lang.Integer;

        String str = ClassDemo.class.toGenericString();
        System.out.println(str);
        return str;
    }

    /*Called after security check for system loader access checks have been made.*/
    /* @CallerSensitive 注解的作用 */
    /** 见 http://openjdk.java.net/jeps/176 。
     总结就是说 jdk内有些方法，jvm的开发者认为这些方法危险，不希望开发者调用，就把这种危险的方法用 @CallerSensitive修饰，并在“jvm”级别检查。

     如Reflection.getCallerClass()方法规定，调用它的对象，必须有 @CallerSensitive 注解，否则 报异常 Exception in thread "main" java.lang.InternalError: CallerSensitive annotation expected at frame 1
     @CallerSensitive 有个特殊之处，必须由 启动类classloader加载（如rt.jar ），才可以被识别。 所以rt.jar下面的注解可以正常使用。
     开发者自己写的@CallerSensitive 不可以被识别。 但是，可以利用jvm参数 -Xbootclasspath/a: path 假装自己的程序是启动类。 **/
   /* 启动类加载器（Bootstrap ClassLoader）
    扩展类加载器（Extension ClassLoader）
    应用程序类加载器（Application ClassLoader）
    自定义类加载器*/

    /**
     * 测试
     **/
    public void forName() {
        try {
            Class.forName("rt.java.lang.ClassDemo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   /* -Xbootclasspath:bootclasspath ：让jvm从指定的路径中加载bootclass，用来替换jdk的rt.jar。一般不会用到。
            -Xbootclasspath/a: path ： 被指定的文件追加到默认的bootstrap路径中。
            -Xbootclasspath/p: path ： 让jvm优先于默认的bootstrap去加载path中指定的class。*/

    /*有CallerSensitive注解*/
    @CallerSensitive
    public static void withCallerSensitive() {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }

    /*无注解直接调用*/
    public static void noCallerSensitive() {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }

    /* 执行前jvm 添加参数 -Xbootclasspath/a:D:\workspace\demo\target\classes */
    @CallerSensitive
    public static void withCallerSensitiveAndJvmParam() {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }


    /*最终调用 Constructor.newInstance 无参的Constructor方法*/
    public void newInstance() {
        try {
            Bean c = Bean.class.newInstance(); // ok
            System.out.println("1");
            NoConstructorBean noConstructorBean = NoConstructorBean.class.newInstance(); // 没有无参构造函数
            System.out.println("2");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void isInstance() {
        Bean c = new Bean();
        boolean isInstance = Bean.class.isInstance(c); // true
        System.out.println(isInstance);
    }

    public void getModifiers() {
        int modInt = ClassDemo.class.getModifiers();
        System.out.println(Modifier.toString(modInt));
    }

    /*可以找到 数组里包含对象的类型*/
    public void getComponentType() {

        Class c = ClassDemo.class.getComponentType();
        System.out.println(c);// null

        ClassDemo[] classDemos = {};
        Class c1 = classDemos.getClass().getComponentType();
        System.out.println(c1);// class rt.java.lang.ClassDemo
    }

    // TODO 不知道用法，好像都是返回null
    public void getSigners() {
        ClassDemo[] classDemos = {};
        System.out.println(classDemos.getClass().getSigners());// null

        HashMap<String, String> hash = new HashMap<String, String>();
        System.out.println(hash.getClass().getSigners());// null
    }

    /*如果一个对象作为匿名类出现在一个方法里，返回这个方法名 */
    //原理：1. 使用 native getEnclosingMethod0 方法，返回调用的 1. 类信息  class rt.java.lang.ClassDemo
    //                                                          2 方法名   getAnonymousBean
    //                                                          3. 对象名 ()Lcommonbean/Bean;
    //    如果不是匿名类出现在一个方法里 ，则这一步返回 null
    // 返回值约定必为这3个，可以从 EnclosingMethodInfo(Object[] enclosingInfo) 里看出来
    // 2 . 对参数做校验
    // 3.  遍历 类信息  class rt.java.lang.ClassDemo 的所有方法Methods
    //         比对 Method 的 名字，参数类型，返回值。 返回值也对比的原因是 重写可以修改返回类型为原来的子类
    /* 测试用的匿名方法 */
    public Bean getAnonymousBean() {
        return new Bean() {
        };
    }

    public void getEnclosingMethod() {
        ClassDemo classDemo = new ClassDemo();  // 核心是 getAnonymousBean()
        Method method = classDemo.getAnonymousBean().getClass().getEnclosingMethod();
        System.out.println(method); // public commonbean.Bean rt.java.lang.ClassDemo.getAnonymousBean()

        Bean bean = new Bean();
        Method method2 = bean.getClass().getEnclosingMethod();
        System.out.println(method2); // null
    }

    /*如果一个对象作为匿名类出现在构造方法里，返回这个构造方法*/
    /*原理和上面的差不多*/
    /* 测试用的构造方法  ,*/
    public Object bean;

    public ClassDemo() {
        // 测试用的内部类,在构造方法中
        class EnclosingConstructor {
        }
        bean = new EnclosingConstructor();
    }

    public void getEnclosingConstructor() throws NoSuchMethodException {
        ClassDemo classDemo = new ClassDemo();
        Constructor _constructor = classDemo.bean.getClass().getEnclosingConstructor();
        System.out.println(_constructor); // public rt.java.lang.ClassDemo()

        Bean bean = new Bean();
        Constructor _constructo2 = bean.getClass().getEnclosingConstructor();
        System.out.println(_constructo2); // null
    }


    /*    获取对应类的声明类Class对象*/
    // 测试用的内部类,在外部类下面
    class InnerClass {

    }

    public void getDeclaringClass() {
        Class _class = ClassDemo.class.getDeclaringClass();
        System.out.println(_class); // null

        Class _class1 = this.getClass().getDeclaringClass();
        System.out.println(_class1); // null

        Class _class2 = InnerClass.class.getDeclaringClass();
        System.out.println(_class2); // class rt.java.lang.ClassDemo ,
    }

    /*如果对内部类使用，则会出现内部类对应的外部类*/
    public void getEnclosingClass() {
        Class _class = ClassDemo.class.getEnclosingClass();
        System.out.println(_class); // null

        Class _class2 = InnerClass.class.getEnclosingClass();
        System.out.println(_class2); // class rt.java.lang.ClassDemo ,
    }

    /*
    1、getDeclaringClass
  return the declaring class for this class
  获取这个类的声明类
    2、getEnclosingClass
  return the immediately enclosing class of the underlying class
  获取此内部类直接相关的类
    几乎一样，区别在匿名内部类上
    */
    public Bean diffBetweenEnclosingAndDeclaringClass() {
        return new Bean() {
            // 随便选了一个Object方法做测试
            // 调用方式 classDemo.diffBetweenEnclosingAndDeclaringClass().toString();
            @Override
            public String toString() {
                System.out.println(this.getClass().getEnclosingClass()); //class rt.java.lang.ClassDemo
                System.out.println(this.getClass().getDeclaringClass()); // null
                return super.toString();
            }
        };
    }

    public void getSimpleName() {
        System.out.println(ClassDemo.class.getSimpleName()); //ClassDemo
        System.out.println(ClassDemo.class.getName()); //rt.java.lang.ClassDemo

        ClassDemo[] clsArr = {};
        System.out.println(clsArr.getClass().getSimpleName()); //ClassDemo[]
        System.out.println(clsArr.getClass().getName()); //[Lrt.java.lang.ClassDemo;

        InnerClass innerClass = new InnerClass();
        System.out.println(innerClass.getClass().getSimpleName()); //InnerClass
        System.out.println(innerClass.getClass().getName()); //rt.java.lang.ClassDemo$InnerClass
    }

    public static void main(String[] args) {
        ClassDemo classDemo = new ClassDemo();
        classDemo.getSimpleName();
    }

}
