package com.siyuanblog.blog.dao;

import com.siyuanblog.blog.po.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
