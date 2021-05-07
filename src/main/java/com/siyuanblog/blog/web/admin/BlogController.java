package com.siyuanblog.blog.web.admin;

import com.siyuanblog.blog.po.Blog;
import com.siyuanblog.blog.service.BlogService;
import com.siyuanblog.blog.service.CategoryService;
import com.siyuanblog.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/manage")
    public String list(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       BlogQuery blog, Model model) {
        model.addAttribute("category",categoryService.listCategory());
        model.addAttribute("page", blogService.listBlog(pageable,blog));
        return "admin/manage";
    }

    @PostMapping("/manage/search")
    public String search(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable,blog));
        return "admin/manage :: blogList";
    }
}
