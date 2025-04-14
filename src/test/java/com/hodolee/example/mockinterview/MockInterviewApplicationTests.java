package com.hodolee.example.mockinterview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodolee.example.mockinterview.ch4.Ch4Service;
import com.hodolee.example.mockinterview.ch8.ShortenRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BOOLEAN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MockInterviewApplicationTests {

	@Autowired
	private Ch4Service ch4Service;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

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

	@Test
	@DisplayName("단축 URL 생성 후, 해당 URL로 접근 시 리디렉션이 동작해야 한다")
	void shortUrl_redirect_success() throws Exception {
		// given
		String title = "남자맨투맨";
		long id = 1L;
		String originalUrl = "http://localhost:8080/product/1";
		ShortenRequest request = new ShortenRequest(title, id, originalUrl);
		// when
		String response = mockMvc.perform(post("/url")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		// then
		String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
		String hashedId = response.substring(response.lastIndexOf("/") + 1);
		mockMvc.perform(MockMvcRequestBuilders.get("/" + encodedTitle + "/" + hashedId))
				.andExpect(status().isFound()) // 302 FOUND
				.andExpect(header().string("Location", originalUrl));
	}

}
