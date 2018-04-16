package memcache;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

import java.util.Date;

public class MemcacheManager {
    /* 单例模式 */
    private static volatile MemCachedClient memCachedClient;

    private MemcacheManager() {

    }

    public static MemCachedClient getInstance() {
        if (memCachedClient == null) {
            synchronized (MemcacheManager.class) {
                if (memCachedClient == null) {
                    String[] servers = {"127.0.0.1:11211"};
                    SockIOPool pool = SockIOPool.getInstance();
                    pool.setServers(servers);
                    pool.setFailover(true);
                    pool.setInitConn(10);
                    pool.setMinConn(5);
                    pool.setMaxConn(250);
                    pool.setMaintSleep(30);
                    pool.setNagle(false);
                    pool.setSocketTO(3000);
                    pool.setAliveCheck(true);
                    pool.initialize();
                    memCachedClient = new MemCachedClient();
                    return memCachedClient;
                }
            }
        }
        return memCachedClient;
    }

    public static void main(String[] args) {
        MemCachedClient memCachedClient = MemcacheManager.getInstance();
        /* String 测试 */
        memCachedClient.set("k1", "v1");
        String v1 = (String) memCachedClient.get("k1");
        System.out.println(v1);
        /* Bean 测试 */
        TestBean testBean = new TestBean();
        testBean.setDate(new Date());
        testBean.setNativeInt(2);
        testBean.setString("string");
        memCachedClient.set("bean", testBean);
        TestBean getBean = (TestBean) memCachedClient.get("bean");
        System.out.println(getBean.getNativeInt());
        /* incr /decr  测试 */
        System.out.println("测试incr");
        memCachedClient.set("incr","0"); // 注意:这里value需要为字符串,因为object必须是可以encode,否则报错
        System.out.println(memCachedClient.get("incr"));
        memCachedClient.incr("incr",5);
        System.out.println(memCachedClient.get("incr"));
    }
}
