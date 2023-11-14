package com.doran.redis.script.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.script.key.ScriptMale;
import com.doran.redis.script.repository.ScriptMaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScriptMaleService {
    private final ScriptMaleRepository scriptMaleRepository;

    //저장
    public void save(ScriptMale scriptMale) {
        scriptMaleRepository.save(scriptMale);
    }

    //삭제
    public void delete(String id) {
        scriptMaleRepository.deleteById(id);
    }

    //조회
    public Optional<ScriptMale> find(String id) {
        return scriptMaleRepository.findById(id);
    }
}
