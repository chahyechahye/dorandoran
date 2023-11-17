package com.doran.redis.record.key;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "record")
public class Record {
    @Id
    private String id;

    private Boolean maleAble;
    private Boolean femaleAble;
}
