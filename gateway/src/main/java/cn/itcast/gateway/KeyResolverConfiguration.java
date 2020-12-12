package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * TODO
 *
 * @author tiger
 * @version 1.0
 * @date 2020/12/12
 */
@Configuration
public class KeyResolverConfiguration {

    /**
     * 基于请求路径的限流规则
     *
     * @return KeyResolver
     */
//    @Bean
//    public KeyResolver pathKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
//    }

    /**
     * 基于请求参数的限流规则
     *
     * @return KeyResolver
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("userId")));
    }
}
