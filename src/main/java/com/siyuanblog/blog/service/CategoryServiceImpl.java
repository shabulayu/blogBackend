package com.siyuanblog.blog.service;

import com.siyuanblog.blog.dao.CategoryRepository;
import com.siyuanblog.blog.po.Category;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Category> listCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public Category updateCategory(Long id, Category category) throws NotFoundException {
        Category ca = categoryRepository.getOne(id);
        if (ca == null){
            throw new NotFoundException("not exist");
        }
        BeanUtils.copyProperties(category, ca);

        return categoryRepository.save(ca);
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
