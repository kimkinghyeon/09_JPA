package com.ohgiraffers.section05.access.subsection02.property;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessPropertyTests {
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

    @DisplayName("프로펄티 작동 테스트")
    @Test
    public void accessPropertyTest(){
        //given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("키키코코");
        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.persist(member);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e);
        }
        //then
//        Member foundMember = entityManager.find(Member.class, 1);
//        System.out.println("foundMember = " + foundMember);
//        assertEquals(member, foundMember);

        String jpql = "select a.nickname from member_section05_sub02 a where a.memberNo= 1";
        String registNickName = entityManager.createQuery(jpql,String.class).getSingleResult();
        assertEquals("키키코코님",registNickName);
        System.out.println(member);

    }

}
