package com.siyuanblog.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        System.out.println("---------index-----------");
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
