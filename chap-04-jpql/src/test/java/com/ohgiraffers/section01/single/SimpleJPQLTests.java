package com.ohgiraffers.section01.single;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleJPQLTests {


    /*
     * jpql(java persistence Query Language)
     * jpql은 엔터티 객체를 중심으로 개발할 수 있는 객체 지향 쿼리이다.
     * sql보다 간결하며 특정 DBMS 에 의존하지 않는다.
     * 방언을 통해 해당 DBMS 에 맞는 SQL 을 실행하게 된다
     *
     * */

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
     * jpql의 기본문법
     * select, update, delete 등의 키워드 사용은 sql 과 동일한다.
     * insert는  persist() 메소드를 사용하면 된다.
     * 키워드는 대소문자를 구분하지 않지만, 엔터티와 속성은 대소문자를 구분함에 유의힌다.
     * 엔터티 별칭은 필수로 작성해야 하며 별칭없이 작성하면 에러가 난다,
     *
     * */

    /*
     * jpql 사용방법은 다음과 같다
     * 1. 작성한 jpql(문자열)은 'entityManager.createQuery()' 메소드를 통해 쿼리 객체로 만든다.
     * 2. 쿼리 객체는 'TypedQuery', 'Query' 두 가지가 있다.
     * 2-1. TypeQuery : 반환할 타입을 명확하게 지정하는 방실일 떄 사용하면 쿼리 객체의 메소드를 실행 결과로 지정하는 타입이 반환된다.
     * 2-2. Query : 반환할 타입에서 명확하게 지정할 수 없을 때 사용하며 쿼리 객체 메소드의 실행 결과로  object 또는 object[]이 반환된다.
     *
     * 3. 쿼리 객체에서 제공하는 메소드는 'getSingleResult()' 또는 'getResultList()' 를 호출하며 쿼리를 실행하고 데이터베이스를 조회한다.
     * 3-1. getSingleResult(): 결과가 정확히 한 행일 경우 사용하며 없거나 많으면 예외가 발생한다.
     * 3-1. getResultList(): 결과가 2행 이상일 경우 사용하며 컬렉션을 반환한다. 결과가 없으면 빈 컬렉션을 반환한다.
     * */

    @Test
    public void TypeQuery_를_이용한_단일메뉴_조회_테스트(){

        String jpql = "SELECT m.menuName FROM menu_section01 as m WHERE m.menuCode = 7";

        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);

        String resultMenuName= query.getSingleResult();

        Assertions.assertEquals("민트미역국", resultMenuName);

    }

    @Test
    public void Query를_이용한_단일메뉴_조회_테스트(){

        String jpql ="SELECT m.menuName FROM menu_section01 as m WHERE m.menuCode = 7";
        Query query = entityManager.createQuery(jpql);
        Object resultMenuName = query.getSingleResult();
        System.out.println(resultMenuName);
        Assertions.assertTrue(resultMenuName instanceof String);
        // String 이랑 같으면  true -> 아니면 false 네
        Assertions.assertEquals("민트미역국", resultMenuName);

    }

    @Test
    public void TypedQuery를_이용한_단일행_조회_테스트() {

        //when
        String jpql = "SELECT m FROM menu_section01 as m WHERE m.menuCode = 7";
        // m은 menu_section01 엔터티에 대한 별칭이다. 그래서 generic type 이 Menu 이다.

        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);   //반환 타입을 row와 매핑할 엔티티 타입으로 설정

        Menu foundMenu = query.getSingleResult();

        //then
        Assertions.assertEquals(7, foundMenu.getMenuCode());
        System.out.println(foundMenu);

    }

    @Test
    public void TypedQuery를_이용한_다중행_조회_테스트() {

        //when
        String jpql = "SELECT m FROM menu_section01 as m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);   //반환 타입을 row와 매핑할 엔티티 타입으로 설정

        List<Menu> foundMenuList = query.getResultList();

        //then
        assertNotNull(foundMenuList); //  api.Assertions import 함
        foundMenuList.forEach(System.out::println);

        //for(Menu m: foundList){ System.out.println(m) }
        // = foundMenuList.forEach(m -> System.out.println(m));
        // = foundMenuList.forEach(System.out::println);

    }

    @Test
    public void distinct를_활용한_중복제거_여러_행_조회_테스트() {

        //when
        String jpql = "SELECT DISTINCT m.categoryCode FROM menu_section01 m";
        // category 코드의 결과에서 중복된 요소를 제거하고 결과를 하나만 남긴다 = distinct 의 역할
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        List<Integer> categoryCodeList = query.getResultList();

        //then
        assertNotNull(categoryCodeList);
        categoryCodeList.forEach(System.out::println);

    }

    @Test
    public void in_연산자를_활용한_조회_테스트() {
        /* categoryCode가 6, 10 인 메뉴 목록 조회 출력 */

        //when
        String jpql = "SELECT m FROM menu_section01 m WHERE m.categoryCode IN (6, 10)";
        // 쿼리 결과가  N이다.
        //
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        //then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @Test
    public void like_연산자를_활용한_조회_테스트() {
        /* 마늘이 들어가는 메뉴명을 가진 메뉴 목록 조회 출력 */

        //when
        String jpql = "SELECT m FROM menu_section01 m WHERE m.menuName LIKE '%마늘%'";
        // 결과가  N개다.
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        //then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }




}
