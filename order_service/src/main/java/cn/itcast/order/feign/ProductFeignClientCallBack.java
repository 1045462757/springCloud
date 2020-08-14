package cn.itcast.order.feign;

import cn.itcast.order.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientCallBack implements ProductFeignClient {
    @Override
    public Product findById(Long id) {
        Product product = new Product();
        product.setProductName("熔断降级");
        return product;
    }
}
