package cn.itcast.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品实体类
 *
 * @author 10454
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String productName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 简介
     */
    private String productDesc;

    /**
     * 库存
     */
    private Integer inventory;
}
