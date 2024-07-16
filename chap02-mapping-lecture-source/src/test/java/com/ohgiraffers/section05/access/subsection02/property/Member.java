package com.ohgiraffers.section05.access.subsection02.property;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//@Entity(name = "member_section05_sub02")
@Access(AccessType.PROPERTY)
/*
클래스레벨에 @Access(AccessType.FIELD)를 선언할때
@Id 어노테이션이 필드에 있다면 엔티티를 생서하지 못한다.
* */
public class Member {
    @Id
    @Column(name = "member_no")
    @Access(AccessType.FIELD)
    private int memberNo;
    @Column(name = "member_id")
    @Access(AccessType.FIELD)
    private String memberId;
    @Column(name = "member_pwd")
    @Access(AccessType.FIELD)
    private String memberPwd;
    @Column(name = "nickname")
    @Access(AccessType.FIELD)
    private String nickName;


    public int getMemberNo() {
        return memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public String getNickName() {
        System.out.println("💥💥💥💥 GetNickName() 메소드확인");
        return nickName + "님";
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public void setNickName(String nickName) {
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
