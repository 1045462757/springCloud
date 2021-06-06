package cn.itcast.product.service.impl;

import cn.itcast.product.cache.CacheService;
import cn.itcast.product.domain.Product;
import cn.itcast.product.mapper.ProductMapper;
import cn.itcast.product.service.ProductService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 10454
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private CacheService cacheService;

    @Resource
    private ProductMapper productMapper;

    @Override
    public Product findById(Integer id) {
        String cache = cacheService.getCache("product:" + id, input -> productMapper.get(id));
        return JSONObject.parseObject(cache, Product.class);
    }

    @Override
    public void save(Product product) {
        productMapper.insert(product);
        cacheService.setCache("product:" + product.getId(), JSON.toJSONString(product), 1L, TimeUnit.DAYS);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
        cacheService.deleteCache("product:" + product.getId());
    }

    @Override
    public void delete(Integer id) {
        int delete = productMapper.delete(id);
        if (delete == 1) {
            cacheService.deleteCache("product:" + id);
        }
    }
}
