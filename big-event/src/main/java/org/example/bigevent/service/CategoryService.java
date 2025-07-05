package org.example.bigevent.service;

import org.example.bigevent.pojo.Category;

import java.util.List;

public interface CategoryService {

    public void addCategory(Category category);

    public List<Category> getCurrentUserAll();

    public Category getCurrentCategoryById(Integer id);

    public void updateCategory(Category category);

    public void deleteCategoryById(Integer id);
}
