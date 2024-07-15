package com.ohgiraffers.section04.enumtype;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumTypeMappingTests {
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

    @DisplayName("enum 타입 메핑 테스트 ")
    @Test
    public void enumTypeMappingTests() {
        //given
        Member member = new Member();
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setMemberName("동홍길");
        member.setPhone("010-1345-6789");
        member.setEmail("user01@gmail.com");
        member.setAddress("서울시 종로구");
        member.setEnrollDate(LocalDate.now());
        member.setMemberRole(RoleType.MEMBER);
        member.setStatus("Y");
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
        Member foundMember = entityManager.find(Member.class, member.getMemberNo());
        System.out.println("foundMember = " + foundMember);
        assertEquals(member.getMemberNo(), foundMember.getMemberNo());
    }
}
