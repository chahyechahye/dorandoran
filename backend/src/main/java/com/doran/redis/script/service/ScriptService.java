package com.doran.redis.script.service;

import org.springframework.stereotype.Service;

import com.doran.record_book.entity.RecordBook;
import com.doran.redis.script.key.ScriptFemale;
import com.doran.redis.script.key.ScriptMale;
import com.doran.redis.script.mapper.ScriptMapper;
import com.doran.utils.common.Genders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScriptService {
    private final ScriptMaleService scriptMaleService;
    private final ScriptFemaleService scriptFemaleService;
    private final ScriptMapper scriptMapper;

    //남 여 확인 후 따로 저장
    public void genderCheck(int userId, Genders genders, RecordBook script) {
        if (genders.equals(Genders.MALE)) {
            ScriptMale scriptMale = scriptMapper.toScriptMale(userId, script.getTitle(), script.getScriptNum());

            scriptMaleService.save(scriptMale);
        } else {
            ScriptFemale scriptMale = scriptMapper.toScriptFemale(userId, script.getTitle(), script.getScriptNum());

            scriptFemaleService.save(scriptMale);
        }
    }

}
