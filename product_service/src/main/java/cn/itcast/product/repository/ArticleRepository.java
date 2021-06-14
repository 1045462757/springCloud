package cn.itcast.product.repository;

import cn.itcast.product.domain.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * ArticleRepository
 *
 * @author tiger
 * @version 1.0
 * @date 2021/6/13
 */
@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {


}
