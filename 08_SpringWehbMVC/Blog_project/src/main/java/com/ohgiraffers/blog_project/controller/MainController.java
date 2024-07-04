package com.ohgiraffers.blog_project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView blog(ModelAndView mv) {
        mv.addObject("value1", "Blog Project");
        mv.addObject("value2", "Ohgiraffers");
        mv.setViewName("blog");

        return mv;
    }

}
