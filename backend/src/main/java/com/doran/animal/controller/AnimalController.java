package com.doran.animal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.animal.service.AnimalService;
import com.doran.response.CommonResponseEntity;
import com.doran.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/animal")
public class AnimalController {

	private final AnimalService animalService;

	@GetMapping("/all")
	public ResponseEntity<?> animalList() {
		return CommonResponseEntity.getResponseEntity(SuccessCode.OK, animalService.selectAllAnimal());
	}

	@PostMapping("")
	public ResponseEntity<?> updateProfileAnimal() {
		return ResponseEntity.ok().build();
	}

}
