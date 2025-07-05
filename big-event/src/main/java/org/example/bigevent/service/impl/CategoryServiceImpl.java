package org.example.bigevent.service.impl;

import org.example.bigevent.exception.DuplicateDataException;
import org.example.bigevent.mapper.CategoryMapper;
import org.example.bigevent.pojo.Category;
import org.example.bigevent.service.CategoryService;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void addCategory(Category category) {
        Category category1 = categoryMapper.selectByCategoryName(category.getCategoryName());
        if(category1 != null)throw new DuplicateDataException("分类已存在");
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        int uid = Integer.parseInt(ThreadLocalUtil.get().get("id").toString());
        category.setCreateUser(uid);
        categoryMapper.insertCategory(category);
    }

    @Override
    public List<Category> getCurrentUserAll() {
        int uid = Integer.parseInt(ThreadLocalUtil.get().get("id").toString());
        return categoryMapper.selectByUserId(uid);
    }

    @Override
    public Category getCurrentCategoryById(Integer id) {
       // int uid = Integer.parseInt(ThreadLocalUtil.get().get("id").toString());
        return categoryMapper.selectCurrentById(id);
    }

    @Override
    public void updateCategory(Category category) {
        Category category1 = categoryMapper.selectCurrentById(category.getId());
        if(category1 == null)throw new DuplicateDataException("分类不存在");
        int uid = Integer.parseInt(ThreadLocalUtil.get().get("id").toString());
        if(uid != category1.getCreateUser()) throw new DuplicateDataException("没有修改权限");
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Category category = categoryMapper.selectCurrentById(id);
        if(category == null)throw new DuplicateDataException("分类不存在");
        int uid = Integer.parseInt(ThreadLocalUtil.get().get("id").toString());
        if(uid != category.getCreateUser())throw new DuplicateDataException("没有删除权限");
        categoryMapper.deleteCategoryById(id);
    }


}
