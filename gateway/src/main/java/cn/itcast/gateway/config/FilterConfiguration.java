package cn.itcast.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * FilterConfiguration
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/6
 */
@Configuration
@Slf4j
public class FilterConfiguration {

    @Bean
    @Order(1)
    public GlobalFilter first() {
        return (exchange, chain) -> {
            log.info("pre first filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("after first filter");
            }));
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter second() {
        return (exchange, chain) -> {
            log.info("pre second filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("after second filter");
            }));
        };
    }

    @Bean
    @Order(3)
    public GlobalFilter third() {
        return (exchange, chain) -> {
            log.info("pre third filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("after third filter");
            }));
        };
    }
}
