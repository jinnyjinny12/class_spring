package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class BiDirectionTests {

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
    public void 양방향_연관관계_매핑_조회_테스트() {

        int menuCode = 15;
        int categoryCode = 15;

        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        Category foundCategory = entityManager.find(Category.class, categoryCode);
        Assertions.assertEquals(categoryCode, foundMenu.getCategory());
        Assertions.assertEquals(categoryCode, categoryCode);

        System.out.println(foundMenu);
        System.out.println(foundCategory);
        foundCategory.getMenuList().forEach(System.out::println);

    }

    @Test
    void 양방향_연관관계_주인_객체를_이용한_삽입_테스트(){

        Menu menu = new Menu();
        menu.setMenuName("연관관계 주인");
        menu.setMenuPrice(10000);
        menu.setOrderableStatus("Y");

        menu.setCategory(entityManager.find(Category.class, 4));

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(menu);
        transaction.commit();

        Menu foundMenu = entityManager.find(Menu.class, menu.getMenuCode());
        Assertions.assertEquals(menu.getMenuCode(), foundMenu.getMenuCode());
        System.out.println(foundMenu);


    }

    @Test
    public void 양방향_연관관계_주인이_아닌_객체를_이용한_삽입테스트(){
        Category category = new Category();
        category.setCategoryName("양방향 카테고리");
        category.setRefCategoryCode(null);

        Menu insertMenu = new Menu();
        insertMenu.setMenuName("연관관계 주인");
        insertMenu.setMenuPrice(10000);
        insertMenu.setOrderableStatus("Y");

        List<Menu> list = new ArrayList<>();
        list.add(insertMenu);
        category.setMenuList(list);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(category);
        transaction.commit();
        Category foundCategory = entityManager.find(Category.class, category.getCategoryCode());
        Assertions.assertEquals(category.getCategoryCode(), foundCategory.getCategoryCode());
        System.out.println(foundCategory);


        Menu foudMenu = entityManager.find(Menu.class, insertMenu.getMenuCode());
        System.out.println(foudMenu);

    }


}
