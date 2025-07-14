package edu.kh.project;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

import edu.kh.project.test.Calculator;

// JUnit을 이용한 테스트 코드는 반드시 src/test/java 경로에 위치해야 함!

// src/main/java : 실제 애플리케이션 코드
// src/test/java : 테스트 코드 (JUnit 포함됨)

// 테스트 특징과 규칙
// 1. 기본적으로 JUnit5는 테스트 메서드의 실행 순서를 보장하지 않는다.
// 2. 테스트는 순서에 의존하지 않도록 설계하는 것이 권장된다.
// 3. 테스트는 독립적이어야 하며, 순서에 따라 실패 or 통과하면 안된다.
// -> 테스트가 순서에 의존하면 좋은 테스트가 아님
// 4. 그래도 순서를 따지고 싶다면 @TestMethodOrder / @Order 사용할 수 있다.

@TestMethodOrder(OrderAnnotation.class)
public class CalculatorTest {
	
	private static Calculator calculator;
	
	// @BeforeAll, @AfterAll - static 메서드에서 사용해야 함! (중요)
	@BeforeAll
	static void initAll() {
		// 모든 테스트 실행 전에 1번만 실행
		calculator = new Calculator(); // static 일 때 반드시 추가되어야 함 (중요)
		System.out.println("@BeforeAll - 테스트 시작 전 초기화");
	}
	
	@AfterAll
	static void end() {
		// 모든 테스트가 끝난 뒤 1번만 실행
		System.out.println("@AfterAll - 모든 테스트 완료");
	}
	
	@Test // 테스트 메서드 표시
	@Order(1)
	void testAdd() {
		System.out.println("testAdd 실행");
		assertEquals(9, calculator.add(6, 3), "6 + 3은 9이여야 합니다");
		// assertEquals(expected, actual) : 두 값이 같은가?
	}
	
	@Test
	@Order(2)
	void testSubtract() {
		System.out.println("testSubtract 실행");
		assertEquals(3, calculator.subtract(6, 3), "6 - 3은 3여야 합니다");
	}
	
	@Test
	@Order(3)
	void testMultiply() {
		System.out.println("testMultiply 실행");
		assertEquals(18, calculator.multiply(6, 3), "6 * 3은 18여야 합니다");
	}

	@Test
	@Order(4)
	void testDivide() {
		assertEquals(2, calculator.divide(6, 3), "6 / 3은 2여야 합니다");
	}
	
	@Test
	@Order(5)
	void testTrueFalse() {
		int result = calculator.add(2, 2);
		assertTrue(result == 4, "결과가 4이어야 합니다");
		assertFalse(result == 5, "결과가 5면 안됩니다");
	}
	
	@Test
	@Order(6)
	void testDivideByZero() { // 0으로 나눴을 때 테스트
		System.out.println("testDivideByZero 실행");
		
		// assertThrows(Exception.class, executable) : 예외 발생 여부
		Exception exception = assertThrows(IllegalArgumentException.class, // 예외가 발생해야 pass)
				() -> {  // 람다 표현식 (js의 화살표 함수랑 비슷)
					calculator.divide(10, 0); // 0이면 pass, 아니면 fail (중요)
				});
		
		assertEquals("0으로 나눌 수 없음", exception.getMessage()); // 메시지는 Calculator의 예외 메시지와 똑같이
		
	}
}
