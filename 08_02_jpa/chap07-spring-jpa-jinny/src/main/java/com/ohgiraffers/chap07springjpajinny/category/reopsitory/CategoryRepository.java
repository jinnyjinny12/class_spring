package com.ohgiraffers.chap07springjpajinny.category.reopsitory;

import com.ohgiraffers.chap07springjpajinny.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String name);




}
