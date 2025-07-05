package org.example.bigevent.service;

import org.example.bigevent.pojo.Article;
import org.example.bigevent.pojo.ArticleQuery;
import org.example.bigevent.pojo.PageResult;

public interface ArticleService {
    public PageResult pageList(ArticleQuery query);

    public void addArticle(Article article);

    public Article getArticleById(Integer id);

    public void updateArticle(Article article);
}
