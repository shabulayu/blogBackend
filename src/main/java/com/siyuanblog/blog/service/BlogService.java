package com.siyuanblog.blog.service;

import com.siyuanblog.blog.po.Blog;
import com.siyuanblog.blog.po.Category;
import com.siyuanblog.blog.vo.BlogQuery;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {

    Blog getBlog(Long id);

    Blog saveBlog(Blog blog);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Blog updateBlog(Long id, Blog blog) throws NotFoundException;

    void deleteBlog(Long id);
}
