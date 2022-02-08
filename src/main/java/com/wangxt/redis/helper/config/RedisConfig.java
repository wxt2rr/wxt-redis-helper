package com.wangxt.redis.helper.config;

import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

@Getter
@Setter
public class RedisConfig {
    private String host;

    private int port;

    private String passwd;

    private int maxTotal = 1024;

    private int maxIdle = 200;

    private int maxWaitMillis = 2000;

    private boolean testOnBorrow = false;

    private boolean testOnReturn = false;

    private StampedLock lock = new StampedLock();

    private HashMap<String, JedisPool> mapJedisPool = new HashMap<>();

    private JedisPool newJedisPool(int dbIndex) {
        JedisPool pool = null;
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(config.getMaxTotal());
        config.setMaxIdle(config.getMaxIdle());
        config.setMaxWaitMillis(config.getMaxWaitMillis());
        config.setTestOnBorrow(config.getTestOnBorrow());
        config.setTestOnReturn(config.getTestOnReturn());
        pool = new JedisPool(config, getHost(), getPort(), Protocol.DEFAULT_TIMEOUT * 10, getPasswd(),
                dbIndex, null);
        return pool;
    }

    public Jedis getJedisResource(String dbIndexStr) {
        JedisPool jedisPool = mapJedisPool.get(dbIndexStr);
        if (jedisPool == null) {
            jedisPool = newJedisPool(Integer.parseInt(dbIndexStr));
            long stamp = lock.writeLock();
            try {
                mapJedisPool.put(dbIndexStr, jedisPool);
            } finally {
                lock.unlockWrite(stamp);
            }
        }
        return jedisPool.getResource();
    }
}
