package com.doran.redis.script.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.script.key.ScriptFemale;
import com.doran.redis.script.repository.ScriptFemaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScriptFemaleService {
    private final ScriptFemaleRepository scriptFemaleRepository;

    //저장
    public void save(ScriptFemale ScriptFemale) {
        scriptFemaleRepository.save(ScriptFemale);
    }

    //삭제
    public void delete(String id) {
        scriptFemaleRepository.deleteById(id);
    }

    //조회
    public Optional<ScriptFemale> find(String id) {
        return scriptFemaleRepository.findById(id);
    }
}
