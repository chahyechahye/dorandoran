package com.doran.redis.model.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.model.key.Model;
import com.doran.redis.model.repository.ModelRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public Model save(Model model) {
        return modelRepository.save(model);
    }

    public Optional<Model> findByUserId(int userId) {
        return modelRepository.findById(String.valueOf(userId));
    }
}
