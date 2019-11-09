package middleware.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class RedisCache implements Cache {

    private RedisTemplate<String, Object> redisTemplate;
    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object object = redisTemplate.opsForValue().get(key);
        ValueWrapper obj=(object != null ? new SimpleValueWrapper(object) : null);
        return  obj;
    }
    /*@Override
    public ValueWrapper get(Object key) {
        System.out.println("------缓存获取-------"+key.toString());
        final String keyf = key.toString();
        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    System.out.println("------缓存不存在-------");
                    return null;
                }
                return SerializationUtils.deserialize(value);
            }
        });
        ValueWrapper obj=(object != null ? new SimpleValueWrapper(object) : null);
        System.out.println("------获取到内容-------"+obj);
        return  obj;
    }*/

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println("-------加入缓存------");
        System.out.println("key----:"+key);
        System.out.println("value----:"+value);
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializationUtils.serialize(valuef);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        });
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    @Override
    public void evict(Object o) {
        System.out.println("-------緩存刪除------");
        final String keyf=o.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyf.getBytes());
            }

        });
    }

    @Override
    public void clear() {
        System.out.println("-------緩存清理------");
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }
}
