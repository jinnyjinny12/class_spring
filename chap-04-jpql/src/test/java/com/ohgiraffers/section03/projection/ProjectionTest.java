package com.ohgiraffers.section03.projection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class ProjectionTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
        // 공장을 만듬 -> 영속성켄텍스트에 요청을 보낼 수 있음 ->  sql 쿼리들을 날릴 수있음
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();

    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    /*
    * 프로젝션(projection)
    *  select절에 조회할 대상을 지정하는 것을 프로젝션이라고 한다.
    * (selcet {프로젝션 대상} from)
    *
    * 프로젝션 대상은 4가지 방식이 있다.
    * 1.엔터티 프로젝션
    *   원하는 객체를 바로 조회할 수 있다.
    *   조회된 엔터티는 영속성 컨텍스트에서 관리한다
    *
    * */

    // 1. 엔터티 프로젝션
    @Test
    public void 단일_엔터티_프로젝션_테스트(){
        String jpql = "SELECT m FROM menu_section03 m";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();
        System.out.println(menuList);
        System.out.println(menuList.get(1));
        Assertions.assertNotNull(menuList);

    }



    @Test
    public void 양방향_연관관계_엔터티_프로젝션_테스트(){
        int menuCodeParameter = 3;
        String jpql = "SELECT m.categoryCode FROM bidirection_menu m WHERE m.menuCode= :menuCode";
        BiDirectionCategory categoryOfMenu = entityManager.createQuery(jpql, BiDirectionCategory.class)
                .setParameter("menuCode", menuCodeParameter).getSingleResult();

        Assertions.assertNotNull(categoryOfMenu);
        System.out.println(categoryOfMenu);
        Assertions.assertNotNull(categoryOfMenu.getMenuList());
        categoryOfMenu.getMenuList().forEach(System.out::println);

    }

    // 2. 임베디드타입 프로젝션

    @Test
    public void 임베디드_타입_프로젝션_테스트() {

        //when
        String jpql = "SELECT m.menuInfo FROM embedded_menu m";
        List<MenuInfo> menuInfoList = entityManager.createQuery(jpql, MenuInfo.class).getResultList();

        //then
        Assertions.assertNotNull(menuInfoList);
        menuInfoList.forEach(System.out::println);

    }






}
