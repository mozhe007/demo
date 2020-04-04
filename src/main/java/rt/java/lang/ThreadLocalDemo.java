package rt.java.lang;


/**
 * ThreadLocal用法
 *
 * @author xxp
 */
public class ThreadLocalDemo {
    /*public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocal.ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }*/

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        // 调用set方法， 实际是调用  Thread t = Thread.currentThread();
        // get 和 remove同理
        threadLocal.set("12");
        threadLocal.get();
        threadLocal.remove();
    }

}