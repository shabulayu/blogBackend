package com.siyuanblog.blog.web.admin;

import com.siyuanblog.blog.po.Category;
import com.siyuanblog.blog.po.Tag;
import com.siyuanblog.blog.service.CategoryService;
import com.siyuanblog.blog.service.TagService;
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
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag")
    public String Tag(@PageableDefault(size = 3, sort = {"id"},direction = Sort.Direction.DESC)
                                       Pageable pageable, Model model){

        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tag";
    }

    @GetMapping("/tag/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/addTag";
    }

    @GetMapping("/tag/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/addTag";
    }

    @PostMapping("/tag")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            result.rejectValue("name", "nameError", "This tag already exists");
        }

        if (result.hasErrors()){
            return "admin/addTag";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "Add new tag failed");
        }else {
            attributes.addFlashAttribute("message", "Add new tag successful");
        }
        return "redirect:/admin/tag";
    }

    @PostMapping("/tag/{id}")
    public String edit(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) throws NotFoundException {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            result.rejectValue("name", "nameError", "This tag already exists");
        }

        if (result.hasErrors()){
            return "admin/addTag";
        }
        Tag t = tagService.updateTag(id, tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "Update tag failed");
        }else {
            attributes.addFlashAttribute("message", "Update tag successful");
        }
        return "redirect:/admin/tag";
    }

    @GetMapping("tag/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "Delete tag successful");
        return "redirect:/admin/tag";
    }
}
