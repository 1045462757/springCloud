package cn.itcast.order.feign;

import cn.itcast.order.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-product", fallbackFactory = ProductCallBackFactory.class)
public interface ProductFeignClient {

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    Product findById(@PathVariable Long id);
}
