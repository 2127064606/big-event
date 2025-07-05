package org.example.bigevent.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.bigevent.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    public void insertCategory(Category category);

    public Category selectByCategoryName(String categoryName);

    public List<Category> selectByUserId(int uid);

    public Category selectCurrentById(Integer id);

    public void updateCategory(Category category);

    public void deleteCategoryById(Integer id);
}
