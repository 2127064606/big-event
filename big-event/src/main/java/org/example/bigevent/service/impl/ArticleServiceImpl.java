package org.example.bigevent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.bigevent.mapper.ArticleMapper;
import org.example.bigevent.pojo.Article;
import org.example.bigevent.pojo.ArticleQuery;
import org.example.bigevent.pojo.PageResult;
import org.example.bigevent.service.ArticleService;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageResult pageList(ArticleQuery query) {
        int uid = Integer.parseInt(ThreadLocalUtil.get().get("id").toString());
        query.setUserId(uid);
         PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Article>articles = articleMapper.selectByStateAndCategoryId(query);
        Page<Article>page = (Page<Article>)articles;
        return new PageResult((int)page.getTotal(),page.getResult());
    }

    @Override
    public void addArticle(Article article) {
            article.setCreateTime(LocalDateTime.now());
            article.setUpdateTime(LocalDateTime.now());
            int uid = Integer.parseInt(ThreadLocalUtil.get().get("id").toString());
            article.setCreateUser(uid);
            articleMapper.insertArticle(article);
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.selectById(id);
    }

    @Override
    public void updateArticle(Article article) {
        if(article.getId() == null)throw new RuntimeException("非法请求");
        //TODO: 是否需要本人操作
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }


}
