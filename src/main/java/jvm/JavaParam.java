package jvm;

/**
 * 通过代码说明 Java 引用传递在堆栈上的关系。
 * 可以从JVM的内存空间存放上说明，值传递 和引用传递。
 * 堆（线程共享）：对象、对象的全局变量、数组
 * 栈（线程私有）：声明为局部变量的 基本数据类型、对象引用（是引用，不是对象本身）、方法参数
 * <p>
 * 针对 public Bean bean1 = new Bean("1"); 这句话会创建1个对象new Bean("1")，1个引用 bean1。
 * 如果这句话在方法中，则 bean1在栈上，随方法结束出栈而消失。
 * 如果这句话在方法外，则 bean1在堆上。
 * 显然 bean1 、bean2、str1、str2 相关的对象和对象引用都在堆上。
 *
 * 以setObjA方法举例。进入方法后，创建栈帧时，因为是方法参数，所以会创建 bean 这个引用（在栈上）
 * bean = new Bean("a");这句是对栈上的bean赋值，和外面的bean1没有关系。只是在堆里建了一个new Bean("a")。
 * 随方法结束出栈，bean的引用消失。刚建出来new Bean("a")因为失去所有的 “GC ROOT” 引用而等待垃圾回收
 *
 * 以setObjB方法举例。进入方法后，创建栈帧时，因为是方法参数，所以会创建 bean 这个引用（在栈上）。（以上都和setObjA一致）
 * bean.setId("b");这句是对堆上的对象操作，即外面的成员变量new Bean("1");也正是因为堆是线程共享的，才引发的出了各种线程安全问题。
 * 随方法结束出栈，bean的引用消失。new Bean("1");此刻仍然被 bean2 引用，不会被垃圾回收。
 *
 * 另外举例了String的操作。作为final的对象，String对象并不可变。要改String类型的全局变量，请使用this.str1 = "XXX"
 *
 *
 */
public class JavaParam {

    public Bean bean1 = new Bean("1");
    public Bean bean2 = new Bean("1");
    public String str1 = new String("str1");
    public String str2 = new String("str1");

    void setObjA(Bean bean) {
        bean = new Bean("a");
    }

    void setObjB(Bean bean) {
        bean.setId("b");
    }

    void setStrA(String str) {
        str = new String("strA");
    }

    void setStrB(String str) {
        str.replace("str1", "strA");
        // 或者 str = "strA";
        // 或者 str = str.concat("strA") 原来的str都不会改变。
    }

    public static void main(String[] args) {
        JavaParam javaParam = new JavaParam();
        System.out.println(javaParam.bean1); // 1
        System.out.println(javaParam.bean2); // 1
        System.out.println(javaParam.str1); // str1
        System.out.println(javaParam.str2); // str1

        javaParam.setObjA(javaParam.bean1);
        javaParam.setObjB(javaParam.bean2);
        javaParam.setStrA(javaParam.str1);
        javaParam.setStrB(javaParam.str2);

        System.out.println(javaParam.bean1); // 1
        System.out.println(javaParam.bean2); // b
        System.out.println(javaParam.str1); // str1
        System.out.println(javaParam.str2); // str1
    }

}
