package com.ohgiraffers.section03.bidirection;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "bidirection_category")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    // 연관관계의 주인을 확인해야 한다. mappedBy 는 연관관계의 주인이 아닌 곳에 써줘야 한다
    // mappedby = "상대편 필드"
    @OneToMany(mappedBy = "category")
    private List<Menu> menuList;

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
