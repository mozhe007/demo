package rt.java.lang.ref;

import java.lang.ref.*;

public class ReferenceDemo {

 /*
    SoftReference
    描述有些还有用但并非必需的对象。在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围进行二次回收。*/

    /*
    WeakReference
    描述非必需对象。被弱引用关联的对象只能生存到下一次垃圾回收之前，垃圾收集器工作之后，无论当前内存是否足够，都会回收掉只被弱引用关联的对象。*/

    /*
     *虚引用对象,
     *这个引用存在的唯一目的就是在这个对象被收集器回收时收到一个系统通知，被虚引用关联的对象，和其生存时间完全没关系。
     */
    public void test(){
        SoftReference<Object> soft = new SoftReference<>(new Object());
        WeakReference<Object> weak = new WeakReference<>(new Object());
        WeakReference<String> weakString = new WeakReference<>("abc");
        PhantomReference<Object> phantom = new PhantomReference<>(new Object(), new ReferenceQueue<>());
        // Phantom : 幽魂
        System.out.println(soft.get());
        System.out.println(weak.get());
        System.out.println(weakString.get());
        System.out.println(phantom.get());
        System.gc();
        System.out.println(soft.get());
        System.out.println(weak.get());
        System.out.println(weakString.get());
        System.out.println(phantom.get());
       /*
java.lang.Object@7cd84586
java.lang.Object@30dae81
abc
null
java.lang.Object@7cd84586
null
abc
null*/
    }
  /*Reerence的代码很多无法分析, 是jvm级别的.从 SoftReference  WeakReference PhantomReference 3个类上的代码上分析,
        他们几乎是一样的. 是垃圾回收器里的代码决定了他们的不同. 总之,麻烦.*/
    /*
    static {
        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        for (ThreadGroup tgn = tg;
             tgn != null;
             tg = tgn, tgn = tg.getParent());
        Thread handler = new ReferenceHandler(tg, "Reference Handler");

        handler.setPriority(Thread.MAX_PRIORITY);
        handler.setDaemon(true);
        handler.start();
} */

    public static void main(String[] args) {
        ReferenceDemo referenceDemo = new ReferenceDemo();
        referenceDemo.test();
    }
}
