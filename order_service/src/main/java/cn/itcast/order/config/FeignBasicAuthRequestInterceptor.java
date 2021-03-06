package cn.itcast.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Feign自定义拦截器
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/6
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 业务逻辑
    }
}
