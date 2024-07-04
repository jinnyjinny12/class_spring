package com.ohgiraffers.chap07springjpajinny.category.service;


import com.ohgiraffers.chap07springjpajinny.category.model.entity.Category;
import com.ohgiraffers.chap07springjpajinny.category.reopsitory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    // 기본생성자를 만든다?
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> categoryList() {
        List<Category> categoryList =categoryRepository.findAll();
        return categoryList;

    }

    public Category categoryFindByName(String name) {
        Category category = categoryRepository.findByCategoryName(name);
        return category;

    }


}
