package com.doran.redis.model.key;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "model")
public class Model {
    @Id
    private String id;

    private Boolean maleAble; //false
    private Boolean femaleAble; //false

    //모델 생성이 가능한지 체킹해주는 레디스객체
    //able = true -> 생성이 가능함
}
