package com.doran;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class TestController {

	//테스트
	@GetMapping("/test")
	public ResponseEntity test(Principal principal) {
		log.info("test");
		log.info("sub : {}", principal.getName());
		return ResponseEntity.ok("test 성공");
	}
}
