package com.hodolee.example.mockinterview.ch4;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Ch4Service {

    @CircuitBreaker(name = "caller", fallbackMethod = "fallbackMethod")
    public void v1Method() {
        log.info("v1Method");
        for (int i = 0; i <10; i++) {
            if (i == 5) {
                log.info("before RuntimeException");
                throw new RuntimeException("too many request");
            }
        }
    }

    private void fallbackMethod(Throwable e) {
        log.info("fallbackMethod : {}", e.getMessage());
    }

}
