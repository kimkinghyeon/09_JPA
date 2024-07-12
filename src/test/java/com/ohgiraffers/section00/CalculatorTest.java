package com.ohgiraffers.section00;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
        // 각 테스트 메서드가 실행되기 전에 실행되어야 하는 코드나 초기화 작업을 할때 사용
    void setUp() {
        calculator = new Calculator();
        System.out.println("BeforeEach : 테스트를 시작합니다. ");
    }

    @AfterEach
        // 각 테스트 메서드가 실행된 후에 실행되어야 하는 후속 작업 및 정리 작업할때 사용
    void tearDown() {
        System.out.println("AfterEach : 테스트 끝!");
    }

    @DisplayName("덧셈 테스트")// 테스트 결과에 대한 이름을 지정할 수 있다.
    @Test
        // 테스트 메소드를 정의할때 사용하는 어노테이션, 테스트 메소드로 등록되고,
        // 코드 검증 및 테스트 결과 확인을 하게 해준다.
    void testAddition() {
        // given
        int a = 5;
        int b = 3;
        // when
        int result = calculator.add(a, b);
        int result2 = calculator.mod(a, b);
        int result3 = calculator.divide(a, b);
        int result4 = calculator.subtract(a, b);
        int result5 = calculator.multiply(a, b);
        // then
        assertEquals(8, result);
        assertEquals(2, result2);
        assertEquals(1, result3);
        assertEquals(2, result4);
        assertEquals(15, result5);
    }


    @DisplayName("뺄셈 테스트")// 테스트 결과에 대한 이름을 지정할 수 있다.
    @Test
        // 테스트 메소드를 정의할때 사용하는 어노테이션, 테스트 메소드로 등록되고,
        // 코드 검증 및 테스트 결과 확인을 하게 해준다.
    void testSubtraction() {
        // given
        int a = 5;
        int b = 3;
        // when

        int result = calculator.subtract(a, b);

        // then
        assertEquals(2, result);

    }
}