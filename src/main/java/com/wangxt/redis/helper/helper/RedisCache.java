package com.wangxt.redis.helper.helper;

import com.wangxt.redis.helper.config.RedisConfig;
import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * @author wangxt
 * @description jedis封装实现类
 * @date 2022/2/8 18:31
 **/
public class RedisCache implements Cache{

    private String dbIndex;

    private RedisConfig redisConfig;

    public RedisCache() {
    }

    public RedisCache(Integer index, RedisConfig redisConfig) {
        this.dbIndex = String.valueOf(index);
        this.redisConfig = redisConfig;
    }

    private Jedis init(){
        if(Objects.isNull(redisConfig)){
            throw new RuntimeException("config is null");
        }
        return redisConfig.getJedisResource(dbIndex);
    }

    /**
     * @author wangxt
     * @description 设置 string
     * @date 2022/2/8 17:56
     **/
    @Override
    public String set(String key, String value) {
        String result = "n";
        try(Jedis jedis = init()){
            result = jedis.set(key, value);
        }

        return result;
    }

    /**
     * @author wangxt
     * @description 获取指定key的value
     * @date 2022/2/8 17:56
     **/
    @Override
    public String get(String key) {
        String result = null;
        try(Jedis jedis = init()){
            result = jedis.get(key);
        }

        return result;
    }
}
