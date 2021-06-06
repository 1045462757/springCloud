package cn.itcast.product.cache;

import java.util.concurrent.TimeUnit;

/**
 * CacheService
 *
 * @author tiger
 * @version 1.0
 * @date 2021/6/6
 */
public interface CacheService {

    /**
     * 设置缓存
     *
     * @param key   key
     * @param value value
     */
    void setCache(String key, String value);

    /**
     * 设置缓存
     *
     * @param key      key
     * @param value    value
     * @param timeout  过期时间
     * @param timeUnit 时间单位
     */
    void setCache(String key, String value, Long timeout, TimeUnit timeUnit);

    /**
     * 删除缓存
     *
     * @param key key
     */
    void deleteCache(String key);

    /**
     * 取缓存
     *
     * @param key key
     * @return String
     */
    String getCache(String key);

    /**
     * 取缓存
     *
     * @param key      key
     * @param callback callback
     * @return String
     */
    String getCache(String key, Callback callback);

    /**
     * 取缓存
     *
     * @param key      key
     * @param callback callback
     * @param timeout  过期时间
     * @param timeUnit 过期时间单位
     * @return String
     */
    String getCache(String key, Callback callback, Long timeout, TimeUnit timeUnit);
}
