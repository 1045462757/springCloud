package cn.itcast.product.controller;

import cn.itcast.product.domain.Product;
import cn.itcast.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 10454
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping
    public String save(@RequestBody Product product) {
        Product byId = productService.findById(product.getId());
        if (Objects.nonNull(byId)) {
            return "已存在";
        }
        productService.save(product);
        return "保存成功";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "删除成功";
    }

    @PutMapping
    public String update(@RequestBody Product product) {
        Product byId = productService.findById(product.getId());
        if (Objects.isNull(byId)) {
            return "不存在";
        }
        productService.update(product);
        return "修改成功";
    }
}
