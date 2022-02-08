package com.wangxt.redis.helper.helper;

/**
 * @author wangxt
 * @description redis抽象类
 * @date 2022/2/8 17:56
 **/
public interface Cache {

    /**
     * @author wangxt
     * @description 设置 string
     * @date 2022/2/8 17:56
     **/
    String set(String key, String value);

    /**
     * @author wangxt
     * @description 获取指定key的value
     * @date 2022/2/8 17:56
     **/
    String get(String key);
}
