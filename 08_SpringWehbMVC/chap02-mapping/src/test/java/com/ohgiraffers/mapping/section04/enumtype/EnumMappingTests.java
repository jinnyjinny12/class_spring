package com.ohgiraffers.mapping.section04.enumtype;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;

public class EnumMappingTests {

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
    public void enum_객체_테스트(){

        // given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("홍길동");
        member.setMemberPwd("pass01");
        member.setAddress("강남구");
        member.setEnrollDate(new Date());
        member.setPhone("001");
        member.setMemberRole(RoleType.USER);
        member.setStatus("Y");


        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(member);
        transaction.commit();
        // 데이터베이스에 영구반영시키겠다 :commit


        //then
        Member foundMember = entityManager.find(Member.class, member.getMemberNo());
        Assertions.assertEquals(member.getMemberNo(), foundMember.getMemberNo());



    }



}
