package com.hodolee.example.mockinterview.ch4;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class Ch4Service {

    private static final String CIRCUIT_BREAKER_NAME = "caller";
    private static final Map<String, Integer> priceMap = new HashMap<>();

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackMethod")
    public int circuitBreakerExample(boolean isFail) {
        log.info("isFail : {}", isFail);
        initData();
        if (isFail) {
            throw new RuntimeException("너무 많은 요청으로 에러발생");
        }

        return priceMap.get("success");
    }

    public int fallbackMethod(boolean isFail, Throwable t) {
        log.info("fallback isFail: {} / t : {}", isFail, t.getMessage());
        initData();

        return priceMap.get("fail");
    }

    public void initData() {
        priceMap.put("success", 10000);
        priceMap.put("fail", 20000);
    }

}
