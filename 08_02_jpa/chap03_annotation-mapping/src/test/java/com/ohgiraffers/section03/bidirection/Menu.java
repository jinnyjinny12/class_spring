package com.ohgiraffers.section03.bidirection;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "bidirection_menu")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_Price")
    private int menuPrice;



    // Category 클래스에 mappedby 어노테이션을 붙임으로 서 Menu 클래스에 있는 Category 를 주인으로 설정해준다
    @JoinColumn(name = "category_code")
    @ManyToOne
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
