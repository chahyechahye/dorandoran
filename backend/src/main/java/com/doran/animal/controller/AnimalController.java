package com.doran.animal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/animal")
public class AnimalController {

	@GetMapping("/all")
	public ResponseEntity<?> animalList() {
		return ResponseEntity.ok().build();
	}

	@PostMapping("")
	public ResponseEntity<?> updateProfileAnimal() {
		return ResponseEntity.ok().build();
	}

}
