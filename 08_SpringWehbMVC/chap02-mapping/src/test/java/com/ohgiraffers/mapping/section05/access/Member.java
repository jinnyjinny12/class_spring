package com.ohgiraffers.mapping.section05.access;

import com.ohgiraffers.mapping.section04.enumtype.RoleType;
import jakarta.persistence.*;

import java.util.Date;

/*
*
* /**
 * 필드 접근이 기본 값이므로 해당 설정은 제거해도 동일하게 동작한다.
 * 또한 필드 레벨과 프로퍼티 레벨을 모두 선언하면 프로퍼티 레벨을 우선으로 한다.
 */



@Entity(name = "member_section05")
@Table(name = "tbl_member_section05")
@Access(AccessType.FIELD)
public class Member {

        //2. 필드레벨 :해당 필드에 대해서 필드 접근 방식을 적용한다.
    @Id //프라이머리키를 설정 -> 설정안해주면 오류남
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD) // 우선순위 ????? 값을 넣어주는 access 의 우선순위가 @access(tpye.property) 필드가 더 높음
    private int memberNo;

    @Column(name = "member_ID")
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickName")
    @Transient
    private String nickName;

    public Member() {
    }

    public Member(int memberNo, String memberId, String memberPwd, String nickName) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
