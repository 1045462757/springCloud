package cn.itcast.product.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * CacheServiceImpl
 *
 * @author tiger
 * @version 1.0
 * @date 2021/6/6
 */
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${redis.expiration.time}")
    private Long timeout;

    @Value("${redis.expiration.timeUnit}")
    private String timeUnit;

    @Override
    public void setCache(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setCache(String key, String value, Long timeout, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    @Override
    public void deleteCache(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public String getCache(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String getCache(String key, Callback callback) {
        return doGetCache(key, callback, this.timeout, TimeUnit.valueOf(this.timeUnit));
    }

    @Override
    public String getCache(String key, Callback callback, Long timeout, TimeUnit timeUnit) {
        return doGetCache(key, callback, timeout, timeUnit);
    }

    private String doGetCache(String key, Callback callback, Long timeout, TimeUnit timeUnit) {
        String result = getCache(key);
        if (Objects.isNull(result)) {
            Object execute = callback.execute(key);
            if (Objects.nonNull(execute)) {
                setCache(key, JSON.toJSONString(execute), timeout, timeUnit);
            }
            return JSON.toJSONString(execute);
        }
        return result;
    }
}
