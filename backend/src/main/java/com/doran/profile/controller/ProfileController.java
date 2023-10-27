package com.doran.profile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.profile.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/my")
    public ResponseEntity<?> childProfileList() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<?> createChildProfile() {
        return ResponseEntity.ok().build();
    }
}
