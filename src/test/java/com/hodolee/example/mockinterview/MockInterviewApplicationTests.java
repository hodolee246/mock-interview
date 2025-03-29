package com.hodolee.example.mockinterview;

import com.hodolee.example.mockinterview.ch4.Ch4Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockInterviewApplicationTests {

	@Autowired
	Ch4Service ch4Service;

	@Test
	@DisplayName("많은 요청이 들어오면 서킷브레이크가 열린다.")
	void circuitBreakerTest() {
		try {
			for (int i = 0; i < 3; i++) {  // 최소 2번 이상 호출 필요
				ch4Service.v1Method();
			}
		} catch (Exception e) {
			System.out.println("예외 발생: " + e.getMessage());
		}
	}

}
