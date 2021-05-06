package com.siyuanblog.blog.web.admin;

import com.siyuanblog.blog.dao.CategoryRepository;
import com.siyuanblog.blog.po.Category;
import com.siyuanblog.blog.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String Category(@PageableDefault(size = 3, sort = {"id"},direction = Sort.Direction.DESC)
                                       Pageable pageable, Model model){

        model.addAttribute("page", categoryService.listCategory(pageable));
        return "admin/category";
    }

    @GetMapping("/category/input")
    public String input(Model model) {
        model.addAttribute("category", new Category());
        return "admin/addCategory";
    }

    @GetMapping("/category/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("category", categoryService.getCategory(id));
        return "admin/addCategory";
    }

    @PostMapping("/category")
    public String post(@Valid Category category, BindingResult result, RedirectAttributes attributes){
        Category category1 = categoryService.getCategoryByName(category.getName());
        if (category1 != null){
            result.rejectValue("name", "nameError", "This category already exists");
        }

        if (result.hasErrors()){
            return "admin/addCategory";
        }
        Category c = categoryService.saveCategory(category);
        if (c == null) {
            attributes.addFlashAttribute("message", "Add new failed");
        }else {
            attributes.addFlashAttribute("message", "Add new successful");
        }
        return "redirect:/admin/category";
    }

    @PostMapping("/category/{id}")
    public String edit(@Valid Category category, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) throws NotFoundException {
        Category category1 = categoryService.getCategoryByName(category.getName());
        if (category1 != null){
            result.rejectValue("name", "nameError", "This category already exists");
        }

        if (result.hasErrors()){
            return "admin/addCategory";
        }
        Category c = categoryService.updateCategory(id, category);
        if (c == null) {
            attributes.addFlashAttribute("message", "Update failed");
        }else {
            attributes.addFlashAttribute("message", "Update successful");
        }
        return "redirect:/admin/category";
    }

    @GetMapping("category/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        categoryService.deleteCategory(id);
        attributes.addFlashAttribute("message", "Delete successful");
        return "redirect:/admin/category";
    }
}
