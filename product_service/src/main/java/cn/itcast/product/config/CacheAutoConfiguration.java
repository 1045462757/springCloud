//package cn.itcast.product.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.Cache;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.interceptor.CacheErrorHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * CacheAutoConfiguration
// *
// * @author tiger
// * @version 1.0
// * @date 2021/6/5
// */
//@Configuration
//@Slf4j
//public class CacheAutoConfiguration extends CachingConfigurerSupport {
//
//    @Override
//    @Bean
//    public CacheErrorHandler errorHandler() {
//
//        return new CacheErrorHandler() {
//            @Override
//            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
//                log.error("redis异常:key=[{}]", o, e);
//            }
//
//            @Override
//            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
//                log.error("redis异常:key=[{}]", o, e);
//            }
//
//            @Override
//            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
//                log.error("redis异常:", e);
//            }
//
//            @Override
//            public void handleCacheClearError(RuntimeException e, Cache cache) {
//                log.error("redis异常:", e);
//            }
//        };
//    }
//}
