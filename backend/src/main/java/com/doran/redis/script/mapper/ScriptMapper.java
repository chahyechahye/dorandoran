package com.doran.redis.script.mapper;

import org.mapstruct.Mapper;

import com.doran.record_book.dto.res.ScriptResDto;
import com.doran.redis.script.key.ScriptFemale;
import com.doran.redis.script.key.ScriptMale;

@Mapper(componentModel = "spring")
public interface ScriptMapper {
    ScriptMale toScriptMale(int id, String title, int scriptNum);

    ScriptFemale toScriptFemale(int id, String title, int scriptNum);

    ScriptResDto toScriptResDto(ScriptMale scriptMale);
}
