//package cn.itcast.product.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
///**
// * redis配置类
// *
// * @author tiger
// * @version 1.0
// * @date 2020/8/23
// */
//@Configuration
//@EnableCaching
//public class RedisConfig {
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        redisTemplate.afterPropertiesSet();
//        setSerializer(redisTemplate);
//        return redisTemplate;
//    }
//
//    private void setSerializer(RedisTemplate<String, String> template) {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//    }
//
////    /**
////     * 设置过期时间
////     */
////    @Bean
////    public CacheManager cacheManager(RedisConnectionFactory factory) {
////        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
////                .entryTtl(Duration.ofDays(1))
////                .disableCachingNullValues()
////                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
////        return RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();
////    }
////
////    /**
////     * 设置参数名
////     */
////    @Bean
////    public KeyGenerator keyGenerator() {
////        return (o, method, objects) -> {
////            StringBuilder stringBuilder = new StringBuilder();
////            stringBuilder.append(o.getClass().getName());
////            stringBuilder.append(":").append(method.getName());
////            for (Object object : objects) {
////                stringBuilder.append(":").append(object.toString());
////            }
////            return stringBuilder.toString();
////        };
////    }
//
//}
//
