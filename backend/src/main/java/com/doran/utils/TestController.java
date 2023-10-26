package com.doran.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.animal.dto.res.AnimalDto;
import com.doran.animal.service.AnimalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/test")
public class TestController {
	private final AnimalService animalService;

	@GetMapping("/animal/{id}")
	public ResponseEntity<AnimalDto> selectAnimal(@PathVariable int id) {
		return ResponseEntity.ok(animalService.selectAnimal(id));
	}
}
