package cn.itcast.product.controller;

import cn.itcast.product.entity.Product;
import cn.itcast.product.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Value("${server.port}")
    private String port;

    @Value("${spring.cloud.client.ip-address}") //spring cloud 自动的获取当前应用的ip地址
    private String ip;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        product.setProductName(ip + ":" + port);
        return product;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody Product product) {
        if (Objects.isNull(product)) {
            return "没有数据";
        }
        productService.save(product);
        return "保存成功";
    }
}
