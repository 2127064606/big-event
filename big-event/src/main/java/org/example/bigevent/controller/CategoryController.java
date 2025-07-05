package org.example.bigevent.controller;


import org.example.bigevent.pojo.Category;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.addCategory(category);
        return Result.success();
    }

    @GetMapping
    public Result getCurrentAll(){
        List<Category>categories = categoryService.getCurrentUserAll();
        return Result.success(categories);
    }

    @GetMapping("/{id}")
    public Result getCurrentById(@PathVariable("id") Integer id){
        Category category = categoryService.getCurrentCategoryById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody Category category){
        categoryService.updateCategory(category);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        categoryService.deleteCategoryById(id);
        return Result.success();
    }


}
