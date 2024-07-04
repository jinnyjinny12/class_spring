package com.ohgiraffers.mapping.section03.primarykey.Identity;

import jakarta.persistence.*;

import java.util.Date;


@Entity(name = "member_section03")
@Table(name = "tbl_member_section03")
public class Member {

    @Id //프라이머리키를 설정 -> 설정안해주면 오류남
    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_ID")
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickName")
    @Transient
    private String nickName;

    // 테이블 생성시에 이 필드를 만들지 마라 : Transient

    @Column(name = "phone", columnDefinition = "varchar(200) default '010-0000-0000'")
    private String phone;

    // 테이블의 구조를 정의해줌, 아무것도 없으면 기본값으로 초기화 시켜 : columnDefinition

    @Column(name = "address" , unique = true )
    private String address;

    // 이 값은 유일해야해.중복 x : unique = true

    @Column(name = "enroll_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollDate;

    // 날짜, 날짜+시간, 날짜+시간+초
    // @Temporal(TemporalType.TIMESTAMP)
    // @Temporal(TemporalType.DATE)
    // @Temporal(TemporalType.TIME)


    @Column(name = "member_role", nullable = false)
    private String memberRole;
    // null 을 허용하지 않음 : nullable = false

    @Column(name = "status")
    private String status;


    public Member() {
    }


    public Member(int memberNo, String memberId, String memberPwd, String nickName, String phone, String address, Date enrollDate, String memberRole, String status) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", enrollDate=" + enrollDate +
                ", memberRole='" + memberRole + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
