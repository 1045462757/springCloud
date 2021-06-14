package cn.itcast.product.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * Article
 *
 * @author tiger
 * @version 1.0
 * @date 2021/6/13
 */
@Data
@Builder
@Document(collection = "article_info")
public class Article {

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("url")
    private String url;

    @Field("author")
    private String author;

    @Field("tags")
    private List<String> tags;

    @Field("visit_count")
    private Long visitCount;

    @Field("add_time")
    private Date addTime;
}
