package com.ohgiraffers.mapping.section06.compositkey;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


// jpa 복합키 설정해줄 때 하는 것
// 복합키는  n개 이상의 자료형? - 링크테이블같은 경우에 사용됨 두가지의 값이 조인되어서 사용되는 부분에 쓸 수 있음.

// 직렬화시킴(Serializable) -> memberNo 와 memberID  2가지가 primarykey 가 괸

@Embeddable
public class MemberPK implements Serializable {

    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberID;

    public MemberPK() {
    }

    public MemberPK(int memberNo, String memberID) {
        this.memberNo = memberNo;
        this.memberID = memberID;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    // 해시 함수 또는 해시 알고리즘 또는 해시함수 알리즘은 임의의 길의의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberID);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        MemberPK memberPK = (MemberPK) obj;

        return memberNo == memberPK.memberNo && Objects.equals(memberID, memberPK.getMemberID());
    }

    @Override
    public String toString() {
        return "MemberPK [memberNo=" + memberNo + ", memberID=" + memberID + "]";

    }

}
