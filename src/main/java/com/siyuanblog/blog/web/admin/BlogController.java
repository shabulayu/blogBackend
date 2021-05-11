package com.siyuanblog.blog.web.admin;

import com.siyuanblog.blog.po.Blog;
import com.siyuanblog.blog.po.User;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

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

    @GetMapping("/manage/publish")
    public String publish(Model model){
        model.addAttribute("category", categoryService.listCategory());
        model.addAttribute("tag", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin/publish";
    }

    @GetMapping("/manage/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("category", categoryService.listCategory());
        model.addAttribute("tag", tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return "admin/edit";
    }

    @PostMapping("/manage")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User)session.getAttribute("user"));
        blog.setCategory(categoryService.getCategory(blog.getCategory().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "Operation is failed");
        }else {
            attributes.addFlashAttribute("message", "Operation is successful");
        }
        return "redirect:/admin/manage";
    }

    @GetMapping("/manage/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "Delete blog successful");
        return "redirect:/admin/manage";
    }
}
