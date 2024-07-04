package com.ohgiraffers.mapping.section05.access;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;




public class FieldAccessTests {


    private  static EntityManagerFactory entityManagerFactory;
    private  static EntityManager entityManager;


    @BeforeAll
    public static void initFactory() {
        // static 이 붙어서  this 가 붙지 않는다. 자기자신을 지칭하는 거아님?
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        this.entityManager.close();
    }

    @AfterAll
    public static void closeFactory(){
        entityManagerFactory.close();
    }

    @Test
    public void 필드_접근_테스트() {
        Member member = new Member(0, "홍길동", "pass01", "use01");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(member);
        transaction.commit();

        Member foundMember = entityManager.find(Member.class,1);

        Assertions.assertEquals(member, foundMember);

    }


    @Test
    public void fieldAccess_test(){
        Member fieldMember = entityManager.find(Member.class, 1);

        Assertions.assertNotNull(fieldMember);
        System.out.println(fieldMember);

    }

//    @Test
//    public void 조회테스트(){
//        Member member = entityManager.find(Member.class, 1);
//        System.out.println(member);
//    }
// setter 라는 생성자를 사용하지 않는다. 기본생성자만 사용한다.


}
