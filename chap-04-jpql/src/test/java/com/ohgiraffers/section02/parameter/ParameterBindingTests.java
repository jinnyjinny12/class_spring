package com.ohgiraffers.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class ParameterBindingTests {

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


    @Test
    public void 이름_기준_파라미터_바인딩_메뉴_목록_조회_테스트() {

        String menuName = "한우딸기국밥";
        String jpql = "SELECT m FROM menu_section02 m WHERE m.menuName = :menuName";
//        "SELECT m FROM menu_section02 m WHERE m.menuName = 한우딸기국밥" -> entityManager 전달 -> DB 요청 -> 반환된 결과값을
//        menulsit 에 담아줘~~~~~~~~~~~~~~~~~~~~~

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter("menuName", menuName) // 한우딸기국밥을 쿼리에서 menuName 이라는 곳에 전달해줘.
                .getResultList();

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);

    }


    @Test
    public void 위치_기준_파라미터_바인딩_메뉴_목록_조회_테스트() {

        //given
        String menuNameParameter = "한우딸기국밥";

        //when
        String jpql = "SELECT m FROM menu_section02 m WHERE m.menuName = ?1";
//        ? 인덱스 위치를 잡아줌. ? 는 첫번째 ?

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter(1, menuNameParameter) // 1 위치에 "한우딸기국밥"을 전달함.
                .getResultList();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }










}
