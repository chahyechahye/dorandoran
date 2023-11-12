package com.doran.redis.script.key;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RedisHash(value = "script")
public class Script {
    @Id
    private String id;

    private String script;

    private int scriptNum;
}
