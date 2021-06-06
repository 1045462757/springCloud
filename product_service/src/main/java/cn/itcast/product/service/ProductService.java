package cn.itcast.product.service;

import cn.itcast.product.domain.Product;

/**
 * @author 10454
 */
public interface ProductService {

    /**
     * 根据id查询
     */
    Product findById(Integer id);

    /**
     * 保存
     */
    void save(Product product);

    /**
     * 更新
     */
    void update(Product product);

    /**
     * 删除
     */
    void delete(Integer id);
}
