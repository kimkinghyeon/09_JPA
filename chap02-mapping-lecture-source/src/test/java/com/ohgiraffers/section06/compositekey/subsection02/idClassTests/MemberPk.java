package com.ohgiraffers.section06.compositekey.subsection02.idClassTests;

import lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberPk {

    private int memberNo;
    private String memberId;

    // equals

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        MemberPk memberPk = (MemberPk) obj;
        return memberNo == memberPk.memberNo && Objects.equals(memberId,memberPk.memberId);
    }

    //hashCode

    @Override
    public int hashCode() {
        return Objects.hash(memberNo,memberId);
    }


}
