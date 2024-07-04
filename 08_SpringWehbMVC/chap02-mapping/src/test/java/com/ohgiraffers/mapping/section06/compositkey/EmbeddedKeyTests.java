package com.ohgiraffers.mapping.section06.compositkey;

import com.ohgiraffers.mapping.section06.compositkey.Member;
import com.ohgiraffers.mapping.section06.compositkey.MemberPK;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;


public class EmbeddedKeyTests {


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

    /*
    * jpa에서 복합키를 매핑하는 방법에는 두 가지가 있다.
    * 첫번째는 "@EmbeddedId"  어노테이션을 사용하는 방법이다
    * 이 방법은 복합키를 구성하는 필드들을 하나의 클래스로 묶은 뒤, 해당 클래스 @EmbeddedId 어노테이션을 사용하여 매핑하는 것이다.
    * 이 방법은 복합키의 일부 필드만 매핑할 수 있기 때문에 필드 수가 많은 경우 유현한 매핑이 가능하다는 장점이 있다.
    * 두 번째는 @IdClass 어노테이션을 사용하는 방법이다.
    * 이 방법은 복합키를 구성하는 필드들을 별도의 클래스로 분리한 뒤, 해당 클래스 @IdClass 어노테이션 값으로 지정해주는 것이다
    * 이 방ㅂ버은 복합키를 구성하는 모든 필드를 한 번에 매핑할 수 있으며, 별도의 매핑 클래스를 사용하지 않기 때문에 코드가 간결하다는 장점이 있다.
    *
    * 복합키의 매핑에서는 복합키 클래스에 equals 와 hashCode 메서드를 구현해야 한다는 점에서 주의해야 한다,
    * 이는  jpa에서 엔터티 객체의 동일성을 판단하기 위해 필요하다
    * 또한 @GeneratedValue 어노테이션을 사용하여 복합킬를 자동으로 생성하는 것은 불가능하다는 점도 주의해야 한다.
    *
    * 두 방식 모두 복합키 클래스는 영속성 컨텍스트가 관리하지 않는다는 특지잉 있으며 큰 기능적 차이도 존재하지 않는다.
    * 다만 @EmbeddedId 가 조금 더 객체 지향 다운 방법이고 @IDClass 는 데이터 베이스에 가까운 방법이다.
    *
    *
    * */

    @Test
    public void 임베디드_아이디_사용한_복합기_테이블_매핑_테스트(){
        Member member = new Member();
        member.setMemberNo(new MemberPK(1,"user01"));
        member.setMemberPwd("1212");
        member.setNickName("홍길동");


        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();

        Member foundMember =entityManager.find(Member.class, member.getMemberNo());
        Assertions.assertEquals(member.getMemberNo(), foundMember.getMemberNo());


    }

}
