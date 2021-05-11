package com.siyuanblog.blog.web;

import com.siyuanblog.blog.service.BlogService;
import com.siyuanblog.blog.service.CategoryService;
import com.siyuanblog.blog.service.TagService;
import com.siyuanblog.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("category", categoryService.listCategoryTop(5));
        model.addAttribute("tag", tagService.listTagTop(5));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(5));
        return "index";
    }
    @GetMapping("/blog")
    public String blog(){

        System.out.println("---------blog-----------");
        return "blog";
    }
    @GetMapping("/archive")
    public String archive(){

        System.out.println("---------archive-----------");
        return "archive";
    }
    @GetMapping("/about")
    public String about(){

        System.out.println("---------about-----------");
        return "about";
    }
    @GetMapping("/category")
    public String category(){

        System.out.println("---------category-----------");
        return "category";
    }
    @GetMapping("/tag")
    public String tag(){

        System.out.println("---------tag-----------");
        return "tag";
    }
}
