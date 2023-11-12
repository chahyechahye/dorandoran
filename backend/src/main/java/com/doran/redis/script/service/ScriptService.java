package com.doran.redis.script.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.script.key.Script;
import com.doran.redis.script.repository.ScriptRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScriptService {
    private final ScriptRepository scriptRepository;

    //저장
    public void save(Script script) {
        scriptRepository.save(script);
    }

    //삭제
    public void delete(String id) {
        scriptRepository.deleteById(id);
    }

    //조회
    //조회 결과가 있으면 그대로 반환
    //없으면 없다고 그냥 빈 거 보내버림
    public Script findScript(String id) {
        Optional<Script> findScript = scriptRepository.findById(id);

        findScript
            .orElseGet(() -> )
    }
}
