package org.example.bigevent.controller;


import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.ResultMapResolver;
import org.example.bigevent.pojo.Article;
import org.example.bigevent.pojo.ArticleQuery;
import org.example.bigevent.pojo.PageResult;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.service.ArticleService;
import org.example.bigevent.utils.JwtGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

/*    @GetMapping
    public Result getAll(){
        System.out.println("getAll");
        return Result.success("一大批文章");
    }*/


    @PostMapping
    public Result add(@RequestBody @Validated(value = Article.Add.class) Article article){
        articleService.addArticle(article);
        return Result.success();
    }

    @GetMapping
    public Result list(@Validated ArticleQuery query){
        log.info("query:{}",query);
         PageResult p = articleService.pageList(query);
         return Result.success(p);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id){
        Article article = articleService.getArticleById(id);
        return Result.success(article);
    }

    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.updateArticle(article);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id")Integer id){
        articleService.deleteArticleById(id);
        return Result.success();
    }
}
