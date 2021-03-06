package cn.itcast.order.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignConfiguration
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/6
 */
@Configuration
public class FeignConfiguration {

    /**
     * 日志级别
     *
     * @return Level.FULL
     */
    @Bean
    feign.Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Feign拦截器
     *
     * @return FeignBasicAuthRequestInterceptor
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    /**
     * 设置连接超时时间和读取超时时间
     *
     * @return Options
     */
    @Bean
    public Request.Options options() {
        // 连接超时时间默认为10s,读取超时时间默认为60s
        return new Request.Options(5000, 10000);
    }
}
