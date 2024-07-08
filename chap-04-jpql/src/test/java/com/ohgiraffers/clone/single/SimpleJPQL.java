package com.ohgiraffers.clone.single;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class SimpleJPQL {

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







}
