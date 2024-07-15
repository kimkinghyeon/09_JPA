package com.ohgiraffers.section05.access.subsection01.field;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiledAccessTests {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    /*
    @Access()
    jpa 는 엔티티 객체의 필드에 직접 접근하는 방식과 Getter 메소드를 이용하는 방식
    두가지로 엔티티에 접근한다.
     */
    @DisplayName("필드로 접근 테스트")
    @Test
    public void AccessFieldTest() {
        //given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setMemberName("동홍길");
        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(member);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        //then
        Member foundMember = entityManager.find(Member.class, 1);
        System.out.println("foundMember = " + foundMember);
        assertEquals(member, foundMember);
    }
}
