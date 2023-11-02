package com.doran.test;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/rabbit")
@RequiredArgsConstructor
public class RabbitController {
    private final TestService testService;

    @GetMapping("")
    ResponseEntity<?> test() {
        testService.sendMessage(new TestMessage("hi", "jimin"));
        return ResponseEntity.ok("hihi");
    }
}
