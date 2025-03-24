package com.hodolee.example.mockinterview.ch4;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Ch4Controller {

    private final Ch4Service ch4Service;

    @GetMapping("v1/nginx")
    public ResponseEntity<?> v1Method() {
        ch4Service.v1Method();
        return ResponseEntity.ok().build();
    }

    @GetMapping("v2/nginx")
    public ResponseEntity<?> v2Method() {
        ch4Service.v2Method();
        return ResponseEntity.ok().build();
    }
}
