package com.hodolee.example.mockinterview.ch4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Ch4Service {

    private static int callCount;

    public void v1Method() {
        incrementCallCount();
    }

    public void v2Method() {
        incrementCallCount();
    }

    private static void incrementCallCount() {
        log.info("before call count: {}", callCount);
        callCount++;
        log.info("after call count: {}", callCount);
    }

}
