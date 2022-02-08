package com.wangxt.redis.helper.helper;

import com.wangxt.redis.helper.config.RedisConfig;
import com.wangxt.redis.helper.pojo.RedisDBIdentity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RedisHelper {

    // 存储 dbIndex 及对应的操作类实例
    private final Map<Integer, Cache> map;

   /**
    * @author wangxt
    * @description 检查 DB 序号的合法性
    * @date 2022/2/818:00
    **/
    private boolean checkDbIndex(int index) {
        return index >= 0 && index <= 255;
    }

    public RedisHelper(RedisConfig redisConfig, Set<Integer> assignedDbIndexes) {
        map = new HashMap<>(assignedDbIndexes.size());
        assignedDbIndexes.stream()
                .filter(this::checkDbIndex)
                .forEach(i -> map.put(i, new RedisCache(i, redisConfig)));
    }

    /**
     * @author wangxt
     * @description 获取 Redis 操作类实例
     * @date 2022/2/8 18:01
     **/
    public Cache get(RedisDBIdentity identity) {
        if (!map.containsKey(identity.getIndex())) {
            throw new IllegalArgumentException("incorrect db index");
        }
        return map.get(identity.getIndex());
    }

}
