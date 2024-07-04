package com.ohgiraffers.section02.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class CategoryAndMenuTests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }
    @BeforeEach
    public void initManager() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @AfterAll
    public static void closeFactory(){
        entityManagerFactory.close();
    }

    @Test
    public void 일대다_연관관계_객체_그래프_탐색을_이용한_조회_테스트() {

        int categoryCode = 10;
        CategoryAndMenu categoryAndMenu = entityManager.find(CategoryAndMenu.class, categoryCode);
        Assertions.assertNotNull(categoryAndMenu);
        System.out.println(categoryAndMenu);

    }

    // 부모엔터티가 categorty code 이기 때문에 먼저 등록된다
    // 그 부모에 값을 넣어준다_menu 를 통해서
    // 값을 받아와서 추가로 업데이트해서 값을 넣어줌,


    @Test
    void 일대다_연관관계_객체_삽입_테스트() {
        CategoryAndMenu categoryAndMenu = new CategoryAndMenu();
        categoryAndMenu.setCategoryCode(777);
        categoryAndMenu.setCategoryName("일대다 아이스크림");
        categoryAndMenu.setRefCategoryCode(null);

        List<Menu> menuList = new ArrayList<>();
        Menu menu = new Menu();
        menu.setMenuCode(777);
        menu.setMenuName("일대다 아이스크림");
        menu.setMenuPrice(80000);
        menu.setOrderableStatus("Y");
        menu.setCategoryCode(categoryAndMenu.getCategoryCode());

        menuList.add(menu);
        categoryAndMenu.setMenuList(menuList);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(categoryAndMenu);
        tx.commit();

        CategoryAndMenu foundCategoryAndMenu = entityManager.find(CategoryAndMenu.class, 888);
        System.out.println(foundCategoryAndMenu);

    }

}
