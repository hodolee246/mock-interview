package com.hodolee.example.mockinterview;

import com.hodolee.example.mockinterview.ch4.Ch4Service;
import com.hodolee.example.mockinterview.ch4.searcher.BlogSearcher;
import com.hodolee.example.mockinterview.ch4.searcher.dto.BlogSearchDto;
import com.hodolee.example.mockinterview.ch4.searcher.dto.ExternalApiResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class MockInterviewApplicationTests {

	@Autowired
	private Ch4Service ch4Service;

	@Mock
	private BlogSearcher kakaoSearcher;

	@Mock
	private BlogSearcher naverSearcher;

	@InjectMocks
	private Ch4Service blogSearcherService;

	private static final boolean IS_METHOD_FAIL = Boolean.TRUE;
	private static final int FAIL_PRICE = 20000;
	private static final boolean IS_METHOD_SUCCESS = Boolean.FALSE;
	private static final int SUCCESS_PRICE = 10000;

	@Test
	@DisplayName("메서드가 성공하면 서킷브레이크가 열리지 않아 성공금액이 반환된다.")
	void successCircuitBreakerExample() {
		int response = ch4Service.circuitBreakerExample(IS_METHOD_SUCCESS);
		assertThat(response).isEqualTo(SUCCESS_PRICE);
	}

	@Test
	@DisplayName("메서드가 실패하면 서킷브레이크가 열려 fallbackMethod의 실패금액이 반환된다.")
	void failCircuitBreakerExample() {
		int response = ch4Service.circuitBreakerExample(IS_METHOD_FAIL);
		assertThat(response).isEqualTo(FAIL_PRICE);
	}

	@Test
	@DisplayName("카카오 블로그 검색하다 서킷브레이크가 열리면 네이버 블로그로 검색한다.")
	void searchKakaoOrNaver() {
		doThrow(new RuntimeException("예외 발생"))
				.when(kakaoSearcher).searchBlog(any(BlogSearchDto.class));

		ExternalApiResponseDto response = blogSearcherService.getKakaoBlog("테스트 쿼리", "accuracy", 1);

		Mockito.verify(naverSearcher).searchBlog(any(BlogSearchDto.class));
		assertThat(response).isNotNull();
	}

}
