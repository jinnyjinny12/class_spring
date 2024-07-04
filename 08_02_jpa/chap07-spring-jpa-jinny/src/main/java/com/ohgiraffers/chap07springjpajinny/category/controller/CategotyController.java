package com.ohgiraffers.chap07springjpajinny.category.controller;


import com.ohgiraffers.chap07springjpajinny.category.model.entity.Category;
import com.ohgiraffers.chap07springjpajinny.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class CategotyController {
    private CategoryService categoryService;

    @Autowired
    public CategotyController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView categoryPage(ModelAndView modelAndView,@RequestParam(value = "search", required = false) String name) {

        if(name !=null && !name.equals("")) {
            Category category = categoryService.categoryFindByName(name);
            modelAndView.addObject("category",category);
            modelAndView.setViewName("category/search");

        }else {
            List<Category> categoryList = categoryService.categoryList();
            modelAndView.addObject("categorys", categoryList);
            modelAndView.setViewName("category/page");
        }

        return modelAndView;


    }


}
