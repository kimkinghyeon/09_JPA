package com.ohgiraffers.section05.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GroupFunctionTests {
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
    그룹함수
    -> JPQL 에서 사용하는 그룹함수랑 , 기존 SQL 에서 사용하는 그룹ㅎ마수느 차이가 거의 없다.
    Count , Sum ,Avg , Max , Min

    주의사항
    1. 그룹함수의 반환 타입은 결과 값이 정수면 Long 실무녀 Double 을 반환한다.
    2. 값이 없는 상태에서 Count 를 제외한 그룹함수는 null 이 되고 Count 는 0이 된다.
    3. 그룹함수의 반환 자료형은 Long or Double 형이기 때문에 Having 절에서
       그룹함수 결과 값과 비교하기 위한 파라미터 타입은 Long or Double 로 해야된다.
    * */

    @DisplayName("특정 카테고리의 등록된 메뉴 수 조회")
    @Test
    public void test1() {

        //given

        int categoryCodeParameter = 2;
        //when
        String jpql = """
                select count (m.menuPrice)
                from menu_section05 m
                where m.categoryCode = :categoryCode
                """;
        long countOfMenu = entityManager.createQuery(jpql, Long.class).setParameter("categoryCode", categoryCodeParameter).getSingleResult();
        //then
        assertTrue(countOfMenu >= 0);
        System.out.println("countOfMenu = " + countOfMenu);

    }

    @DisplayName("count 를 제외한 다른 그룹함수의 조회결과가 없는 경우 테스트")
    @Test
    public void test2() {

        //given

        int categoryCodeParameter = 2;
        //when
        String jpql = """
                select sum (m.menuPrice)
                from menu_section05 m
                where m.categoryCode = :categoryCode
                """;


        //then
//        assertThrows(NullPointerException.class, () -> {
//            long countOfMenu = entityManager.createQuery(jpql, Long.class)
//                    .setParameter("categoryCode", categoryCodeParameter).getSingleResult();
//        });
        assertDoesNotThrow(() -> {
            long countOfMenu = entityManager.createQuery(jpql, Long.class)
                    .setParameter("categoryCode", categoryCodeParameter).getSingleResult();
        });
//        assertTrue(countOfMenu >= 0);
//        System.out.println("countOfMenu = " + countOfMenu);

    }
}
