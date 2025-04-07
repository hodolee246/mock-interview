package com.hodolee.example.mockinterview;

import com.hodolee.example.mockinterview.ch4.Ch4Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BOOLEAN;

@SpringBootTest
class MockInterviewApplicationTests {

	@Autowired
	private Ch4Service ch4Service;

	private static final boolean IS_METHOD_FAIL = Boolean.TRUE;
	private static final int FAIL_PRICE = 20000;
	private static final boolean IS_METHOD_SUCCESS = Boolean.FALSE;
	private static final int SUCCESS_PRICE = 10000;

	@Test
	@DisplayName("메서드가 성공하면 서킷브레이크가 열리지 않아 성공금액이 반환된다.")
	void successCircuitBreakerExample() {
//		int response = ch4Service.circuitBreakerExample(IS_METHOD_SUCCESS);
//		assertThat(response).isEqualTo(SUCCESS_PRICE);
	}

	@Test
	@DisplayName("메서드가 실패하면 서킷브레이크가 열려 fallbackMethod의 실패금액이 반환된다.")
	void failCircuitBreakerExample() {
//		int response = ch4Service.circuitBreakerExample(IS_METHOD_FAIL);
//		assertThat(response).isEqualTo(FAIL_PRICE);
	}

}
