package com.doran.redis.script.mapper;

import org.mapstruct.Mapper;

import com.doran.record_book.dto.res.ScriptResDto;
import com.doran.redis.script.key.Script;

@Mapper(componentModel = "spring")
public interface ScriptMapper {
    Script toScript(int id, String script, int scriptNum);

    ScriptResDto toScriptResDto(Script script);
}
