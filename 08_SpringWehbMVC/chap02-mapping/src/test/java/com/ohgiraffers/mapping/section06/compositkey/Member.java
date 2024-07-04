package com.ohgiraffers.mapping.section06.compositkey;

import jakarta.persistence.*;
import org.junit.jupiter.api.Test;

@Entity(name = "member_section06")
@Table(name = "tbl_member_section06")
public class Member {
    @EmbeddedId
    private MemberPK memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;
    @Column(name = "nick_name")
    private String nickName;

    public Member() {
    }

    public Member(MemberPK memberNo, String memberId, String memberPwd, String nickName) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
    }

    public MemberPK getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(MemberPK memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

