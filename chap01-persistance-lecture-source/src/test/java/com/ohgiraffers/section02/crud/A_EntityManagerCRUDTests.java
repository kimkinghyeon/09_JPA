package com.ohgiraffers.section02.crud;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerCRUDTests {

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

    @DisplayName("메뉴 코드로 메뉴 조회 테스트")
    @Test
    public void selectMenuByMenuCodeTest() {
        // given
        int menuCode = 2;
        // when
        Menu menu = entityManager.find(Menu.class, menuCode);
        // then
        Assertions.assertNotNull(menu);

        Assertions.assertEquals(menuCode, menu.getMenuCode());
        System.out.println("menu = " + menu);
        Menu menu2 = entityManager.find(Menu.class, 2);
        System.out.println("menu2 = " + menu2);
    }

    @DisplayName("새로운 메뉴 추가 테스트")
    @Test
    public void insertNewMenuTest() {
        // given
        Menu menu = new Menu();
        menu.setMenuName("JPA 테스트용 신규 메뉴");
        menu.setMenuPrice(3000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");
        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); // 트렌젝션 시작을 선언
        try {
            entityManager.persist(menu);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        // then
        Assertions.assertTrue(entityManager.contains(menu));
        // 영속성 컨텍스트에 menu 가 있는지 확인
    }
    @DisplayName("메뉴 이름 수정 테스트")
    @Test
    public void modifyMenuNameTest(){
        // given
        Menu menu = entityManager.find(Menu.class,22);
        System.out.println("menu = " + menu);
        String menuNameChange = "생갈치초고추장와사비무침";
        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            menu.setMenuName(menuNameChange);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        // then
        Assertions.assertEquals(menuNameChange,menu.getMenuName());
        Assertions.assertEquals(menuNameChange , entityManager.find(Menu.class,22).getMenuName());
    }
    @DisplayName("메뉴 삭제하기 테스트")
    @Test
    public void deleteMenu(){
        // given
        Menu menuToRemove = entityManager.find(Menu.class,22);
        System.out.println("menuToRemove = " + menuToRemove);
        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.remove(menuToRemove);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        // then
        Menu removeMenu =entityManager.find(Menu.class,22);
        Assertions.assertEquals(null,removeMenu);
    }
}
