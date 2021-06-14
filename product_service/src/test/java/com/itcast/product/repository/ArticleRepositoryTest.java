package com.itcast.product.repository;


import cn.itcast.product.ProductApplication;
import cn.itcast.product.domain.Article;
import cn.itcast.product.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * ArticleRepositoryTest
 *
 * @author tiger
 * @version 1.0
 * @date 2021/6/13
 */
@SpringBootTest(classes = ProductApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ArticleRepositoryTest {

    @Resource
    private ArticleRepository articleRepository;

    @Test
    public void test() {
        Article article = Article.builder()
                .title("第一篇")
                .url("第一篇url")
                .author("masami")
                .visitCount(0L)
                .addTime(new Date())
                .build();

        articleRepository.save(article);
    }

    @Test
    public void getAll() {
        System.out.println(articleRepository.findAll());
    }
}
