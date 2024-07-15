package com.ohgiraffers.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class EntityMappingTest {
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

    @DisplayName("테이블 만들기 테스트")
    @Test
    public void createTableTest() {
        // given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setMemberName("홍길동");
        member.setPhone("010-2345-6789");
        member.setEmail("user01@gmail.com");
        member.setAddress("서울시 종로구");
        member.setEnrollDate(LocalDate.now());
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");
        // when
        entityManager.persist(member);
        // then
        Member foundMember = entityManager.find(Member.class,member.getMemberNo());
        Assertions.assertEquals(member.getMemberNo(),foundMember.getMemberNo());
    }
}
