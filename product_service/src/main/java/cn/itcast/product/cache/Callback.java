package cn.itcast.product.cache;

/**
 * Callback回调
 *
 * @author tiger
 * @version 1.0
 * @date 2021/6/6
 */
public interface Callback {

    /**
     * 输入输出
     *
     * @param input 输入
     * @return 输出
     */
    Object execute(String key);
}
