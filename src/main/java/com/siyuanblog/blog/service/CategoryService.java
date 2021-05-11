package com.siyuanblog.blog.service;

import com.siyuanblog.blog.po.Category;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    Category saveCategory(Category category);

    Category getCategory(Long id);

    Category getCategoryByName(String name);

    Page<Category> listCategory(Pageable pageable);

    List<Category> listCategory();

    List<Category> listCategoryTop(Integer size);

    Category updateCategory(Long id, Category category) throws NotFoundException;

    void deleteCategory(Long id);

}
