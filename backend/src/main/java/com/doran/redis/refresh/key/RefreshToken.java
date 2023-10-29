package com.doran.redis.refresh.key;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//60초 이후에 레디스에서 삭제 됨
@RedisHash(value = "refreshToken", timeToLive = 865)
public class RefreshToken {
    @Id
    private String userId;

    @Indexed
    private String value;
}
