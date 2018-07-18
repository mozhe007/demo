package redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheTest {

    @Cacheable(cacheNames="redisCache",key="#root.args")
    public String fooString(String id){
        System.out.println("fooString()");
        return "return:"+id;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:spring-cache.xml"});
        RedisCacheTest redisCacheTest = applicationContext.getBean(RedisCacheTest.class);
        System.out.println(redisCacheTest.fooString("james"));
        System.out.println(redisCacheTest.fooString("james"));
    }
}
