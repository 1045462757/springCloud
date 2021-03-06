package cn.itcast.order.feign;

import cn.itcast.order.entity.Product;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Hystrix熔断
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/6
 */
@Component
@Slf4j
public class ProductCallBackFactory implements FallbackFactory<ProductFeignClient> {

    @Override
    public ProductFeignClient create(Throwable throwable) {
        throwable.printStackTrace();
        log.error("ProductService熔断:" + throwable.getMessage());
        return id -> {
            Product product = new Product();
            product.setProductName("熔断降级:" + id);
            return product;
        };
    }
}
