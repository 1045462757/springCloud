package cn.itcast.product.mapper;

import cn.itcast.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * ProductMapper
 *
 * @author tiger
 * @version 1.0
 * @date 2021/6/6
 */
@Mapper
public interface ProductMapper {

    /**
     * 保存页面
     *
     * @param product 商品
     * @return 成功:1,失败:0
     */
    int insert(Product product);

    int delete(Integer id);

    int update(Product product);

    Product get(Integer id);
}
