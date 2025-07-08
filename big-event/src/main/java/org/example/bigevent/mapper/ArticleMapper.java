package org.example.bigevent.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.bigevent.pojo.Article;
import org.example.bigevent.pojo.ArticleQuery;

import java.util.List;

@Mapper
public interface ArticleMapper {
    public void insertArticle(Article article);

    public List<Article> selectByStateAndCategoryId(ArticleQuery query);

    public Article selectById(Integer id);

    public void update(Article article);

    public void deleteById(Integer id);
}
