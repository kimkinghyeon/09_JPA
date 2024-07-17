package com.ohgiraffers.section08.namedquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

public class JpqlNamedQueryTests {
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

    /**/

    @DisplayName("@NamedQuery - findAll")
    @Test
    public void test1() {

        //given


        //when
        List<Menu> menuList = entityManager.createNamedQuery("section08.namedquery.Menu.findAll", Menu.class).getResultList();

        //then
        Assertions.assertThat(menuList).isNotNull();
        menuList.forEach(System.out::println);
    }

    // findByMenuName -> 메뉴이름으로 일치하는 메뉴 찾기
    // 메뉴 이름을 파라미터로 받고, 일치하는 메뉴객체를 반환하는 JPQL

    @DisplayName("🔼")
    @Test
    public void test2() {

        //given

        String menuName = "홍어마카롱";
        //when
        List<Menu> menuList = entityManager.createNamedQuery("section08.namedquery.Menu.findByMenuName", Menu.class)
                .setParameter("menuName",menuName)
                .getResultList();

        //then
        Assertions.assertThat(menuList).isNotNull();
        menuList.forEach(System.out::println);
    }
    @DisplayName("")
    @Test
    public void test3() {

        //given

        List<Menu> menuList = entityManager.createNamedQuery("section08_namedquery.Menu.findByBab", Menu.class)
                .getResultList();

        //then
        Assertions.assertThat(menuList).isNotNull();
        menuList.forEach(System.out::println);
    }
    @DisplayName("")
    @Test
    public void test4() {

        //given
String menuName = "밥";
        //when
        List<Menu> menuList = entityManager.createNamedQuery("section08_namedquery.Menu.findByMenuNameLike", Menu.class)
                .setParameter("menuName",menuName)
                .getResultList();

        //then
        Assertions.assertThat(menuList).isNotNull();
        menuList.forEach(System.out::println);
    }
}
