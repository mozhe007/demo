package redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

public class RedisManager {

    public Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池

    public RedisManager() {
        initialPool();
        initialShardedPool();
        //shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
    }

    /**
     * 初始化非切片池( 对应单机 )
     */
    private void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    /**
     * 初始化切片池( 对应集群 )
     */
    private void initialShardedPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));
        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public Jedis getRedis(){
        RedisManager redisManager = new RedisManager();
        return redisManager.jedis;
    }

    public static void main(String[] args) {
        RedisManager redisManager = new RedisManager();
        redisManager.jedis.set("k1","v1");
        System.out.println(redisManager.jedis.get("k1"));
        System.out.println(redisManager.jedis.get("nilStr"));
        HashMap<String,String> inHashMap = new HashMap<String, String>();
        inHashMap.put("hashk1","v11");
        redisManager.jedis.hmset("hash",inHashMap);
        System.out.println(redisManager.jedis.hmget("hash","hashk1"));
        System.out.println(redisManager.jedis.hmget("hash","hashk2"));


    }


}
